package dk.sdu.cbse.common.movement;

import dk.sdu.cbse.common.services.IEntityComponent;

public class MovementCP implements IEntityComponent {
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
