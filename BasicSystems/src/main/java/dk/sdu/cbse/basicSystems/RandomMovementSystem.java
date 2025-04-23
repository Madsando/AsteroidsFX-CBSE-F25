package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.entitycomponents.RandomMovementControlCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Random;

public class RandomMovementSystem implements ISystemService {
    @Override
    public void update(GameData gameData, World world) {
        Random rng = new Random();

        for (Entity e : world.getEntitiesWithComponent(RandomMovementControlCP.class)) {
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
