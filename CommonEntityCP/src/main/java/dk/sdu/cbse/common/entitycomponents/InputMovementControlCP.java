package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.data.EGameInputs;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;

public class InputMovementControlCP implements IEntityComponent {
    @Override
    public void process(GameData gameData, World world, Entity entity) {
        MovementCP movementCP = entity.getComponent(MovementCP.class);
        if (movementCP != null) {
            movementCP.setForward(gameData.getInputs().isDown(EGameInputs.FORWARD));
            movementCP.setLeft(gameData.getInputs().isDown(EGameInputs.LEFT));
            movementCP.setRight(gameData.getInputs().isDown(EGameInputs.RIGHT));
        }
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
