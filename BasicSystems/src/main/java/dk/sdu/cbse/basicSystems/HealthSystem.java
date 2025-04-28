package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.services.ICustomEntityBehaviour;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.services.ISystemService;

public class HealthSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void update(GameData gameData, World world) {
        for (Entity e : world.getEntitiesWithComponent(HealthCP.class)) {
            HealthCP healthCP = e.getComponent(HealthCP.class);
            ICustomEntityBehaviour behaviour = healthCP.getBehaviour();

            if (isDead(healthCP.getHealth())) {
                if (behaviour != null) {
                    behaviour.process(gameData, world, e);
                }
                world.removeEntity(e);
            }
        }
    }

    public boolean isDead(int health) {
        return health <= 0;
    }

    public boolean isAlive(int health) {
        return health > 0;
    }
}
