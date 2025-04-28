package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.input.EGameInputs;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.InputMovementControlCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.services.ISystemService;

public class MovementInputControlSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void update(GameData gameData, World world) {
        for (Entity e : world.getEntitiesWithComponent(InputMovementControlCP.class)) {
            MovementCP movementCP = e.getComponent(MovementCP.class);
            if (movementCP != null) {
                movementCP.setForward(gameData.getInputs().isDown(EGameInputs.FORWARD));
                movementCP.setLeft(gameData.getInputs().isDown(EGameInputs.LEFT));
                movementCP.setRight(gameData.getInputs().isDown(EGameInputs.RIGHT));
            }
        }

    }
}
