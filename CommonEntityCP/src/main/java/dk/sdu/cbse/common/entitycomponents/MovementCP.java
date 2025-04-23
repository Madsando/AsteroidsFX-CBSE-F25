package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.IEntityComponent;

public class MovementCP implements IEntityComponent {
    private double velocity;
    private double rotationSpeed;
    private boolean left, right, forward;
    private final boolean shouldCullOOB;

    public MovementCP(double velocity, double rotationSpeed, boolean left, boolean right, boolean forward, boolean shouldCullOOB) {
        this.velocity = velocity;
        this.rotationSpeed = rotationSpeed;
        this.left = left;
        this.right = right;
        this.forward = forward;
        this.shouldCullOOB = shouldCullOOB;
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        TransformCP transformCP = entity.getComponent(TransformCP.class);

        if (forward) {
            double angle = Math.toRadians(transformCP.getRotation());
            double changeX = Math.cos(angle);
            double changeY = Math.sin(angle);

            transformCP.setX(transformCP.getX() + changeX * velocity);
            transformCP.setY(transformCP.getY() + changeY * velocity);
        }
        if (left) {
            transformCP.setRotation(transformCP.getRotation() - rotationSpeed);
        }

        if (right) {
            transformCP.setRotation(transformCP.getRotation() + rotationSpeed);
        }

        if (transformCP.getX() < 0) {
            if (shouldCullOOB) {
                world.removeEntity(entity);
            }
            transformCP.setX(gameData.getDisplayWidth());
        } else if (transformCP.getX() > gameData.getDisplayWidth()) {
            if (shouldCullOOB) {
                world.removeEntity(entity);
            }
            transformCP.setX(0);
        }

        if (transformCP.getY() < 0) {
            if (shouldCullOOB) {
                world.removeEntity(entity);
            }
            transformCP.setY(gameData.getDisplayHeight());
        } else if (transformCP.getY() > gameData.getDisplayHeight()) {
            if (shouldCullOOB) {
                world.removeEntity(entity);
            }
            transformCP.setY(0);
        }

    }

    @Override
    public int getPriority() {
        return 4;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
