package dk.sdu.cbse.health;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.ICustomEntityBehaviour;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;

public class HealthSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{HealthCP.class},
                null
        );
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        for (Node node: nodes) {
            HealthCP healthCP = (HealthCP) node.getComponent(HealthCP.class);
            ICustomEntityBehaviour behaviour = healthCP.getBehaviour();

            if (isDead(healthCP.getHealth())) {
                if (behaviour != null) {
                    behaviour.process(gameData, world, world.getEntity(node.getEntityID()));
                }
                world.removeEntity(node.getEntityID());
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
