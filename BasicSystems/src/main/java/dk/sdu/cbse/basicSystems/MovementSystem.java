package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.services.ISystemService;

public class MovementSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void update(GameData gameData, World world) {
        for (Entity entity: world.getEntitiesWithComponent(MovementCP.class)) {
            TransformCP transformCP = entity.getComponent(TransformCP.class);
            MovementCP movementCP = entity.getComponent(MovementCP.class);

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
