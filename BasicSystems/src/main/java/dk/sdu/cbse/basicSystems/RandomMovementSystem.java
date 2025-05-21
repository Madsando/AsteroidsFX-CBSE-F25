package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.services.IEntityComponent;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;
import java.util.Random;

public class RandomMovementSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{RandomMovementControlCP.class, MovementCP.class},
                null
        );
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        Random rng = new Random();

        for (Node node: nodes) {
            MovementCP movementCP = (MovementCP) node.getComponent(MovementCP.class);
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
