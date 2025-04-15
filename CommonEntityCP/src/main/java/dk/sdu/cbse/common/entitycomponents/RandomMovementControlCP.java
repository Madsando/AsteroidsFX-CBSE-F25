package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;

import java.util.Random;

public class RandomMovementControlCP implements IEntityComponent {
    @Override
    public void process(GameData gameData, World world, Entity entity) {
        Random rng = new Random();

        MovementCP movementCP = entity.getComponent(MovementCP.class);
        if (rng.nextInt(90) == 1) {
            movementCP.setRotationSpeed(rng.nextInt(-45, 45));
            movementCP.setLeft(true);
        } else {
            movementCP.setLeft(false);
        }
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
