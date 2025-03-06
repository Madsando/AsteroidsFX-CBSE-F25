package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.data.EGameInputs;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class MovementCP implements EntityComponent {
    private double velocity;
    private double rotationSpeed;
    private boolean left, right, forward;

    public MovementCP(double velocity, double rotationSpeed, boolean left, boolean right, boolean forward) {
        this.velocity = velocity;
        this.rotationSpeed = rotationSpeed;
        this.left = left;
        this.right = right;
        this.forward = forward;
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        PositionCP positionCP = entity.getComponent(PositionCP.class);

        if (forward) {
            double angle = Math.toRadians(positionCP.getRotation());
            double changeX = Math.cos(angle);
            double changeY = Math.sin(angle);

            positionCP.setX(positionCP.getX() + changeX * velocity);
            positionCP.setY(positionCP.getY() + changeY * velocity);
        }
        if (left) {
            positionCP.setRotation(positionCP.getRotation() - rotationSpeed);
        }

        if (right) {
            positionCP.setRotation(positionCP.getRotation() + rotationSpeed);
        }

        if (positionCP.getX() < 0) {
            positionCP.setX(gameData.getDisplayWidth());
        } else if (positionCP.getX() > gameData.getDisplayWidth()) {
            positionCP.setX(0);
        }

        if (positionCP.getY() < 0) {
            positionCP.setY(gameData.getDisplayHeight());
        } else if (positionCP.getY() > gameData.getDisplayHeight()) {
            positionCP.setY(0);
        }

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
