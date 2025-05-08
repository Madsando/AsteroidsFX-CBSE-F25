package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.CullingCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.entitycomponents.RandomMovementControlCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.services.IEntityComponent;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Random;

public class RandomMovementSystem implements ISystemService {
    @Override
    public void update(GameData gameData, World world) {
        Random rng = new Random();

        Class<? extends IEntityComponent>[] components = new Class[]{RandomMovementControlCP.class, MovementCP.class};
        for (Entity e : world.getEntitiesWithComponents(components)) {
            MovementCP movementCP = e.getComponent(MovementCP.class);
            if (rng.nextInt(90) == 1) {
                movementCP.setRotationSpeed(rng.nextInt(-45, 45));
                movementCP.setLeft(true);
            } else {
                movementCP.setLeft(false);
            }
        }
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
