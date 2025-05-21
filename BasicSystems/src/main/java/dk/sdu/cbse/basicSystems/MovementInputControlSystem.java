package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.input.EGameInputs;
import dk.sdu.cbse.common.entitycomponents.InputMovementControlCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;

public class MovementInputControlSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{InputMovementControlCP.class, MovementCP.class},
                null
        );
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        for (Node node : nodes) {
            MovementCP movementCP = (MovementCP) node.getComponent(MovementCP.class);
            movementCP.setForward(gameData.getInputs().isDown(EGameInputs.FORWARD));
            movementCP.setLeft(gameData.getInputs().isDown(EGameInputs.LEFT));
            movementCP.setRight(gameData.getInputs().isDown(EGameInputs.RIGHT));
        }
    }
}
