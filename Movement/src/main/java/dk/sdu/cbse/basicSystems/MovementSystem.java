package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.movement.MovementCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;

public class MovementSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{TransformCP.class, MovementCP.class},
                null
        );
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        for (Node node: nodes) {
            TransformCP transformCP = (TransformCP) node.getComponent(TransformCP.class);
            MovementCP movementCP = (MovementCP) node.getComponent(MovementCP.class);

            if (movementCP.isForward()) {
                double angle = Math.toRadians(transformCP.getRotation());
                double changeX = Math.cos(angle);
                double changeY = Math.sin(angle);

                transformCP.setX(transformCP.getX() + changeX * movementCP.getVelocity());
                transformCP.setY(transformCP.getY() + changeY * movementCP.getVelocity());
            }
            if (movementCP.isLeft()) {
                transformCP.setRotation(transformCP.getRotation() - movementCP.getRotationSpeed());
            }

            if (movementCP.isRight()) {
                transformCP.setRotation(transformCP.getRotation() + movementCP.getRotationSpeed());
            }
        }
    }
}
