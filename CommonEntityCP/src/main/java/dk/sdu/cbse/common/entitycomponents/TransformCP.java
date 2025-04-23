package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.IEntityComponent;

public class TransformCP implements IEntityComponent {
    private double x;
    private double y;
    private double rotation;
    private double radius;

    public TransformCP(double x, double y, double rotation, double radius) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
