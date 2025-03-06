package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class MovementCP implements EntityComponent {
    private double velocity;
    private double rotationSpeed;
    private boolean left, right, forward;

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        // TODO

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
}
