package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EntityComponent;

public class ShapeCP implements EntityComponent {
    private double[] polygonCoordinates;
    private double radius;
    private int[] color;

    public ShapeCP(double[] polygonCoordinates, double radius, int[] color) {
        this.polygonCoordinates = polygonCoordinates;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        // TODO
    }

    @Override
    public int getPriority() {
        return 3;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setPolygonCoordinates(double[] polygonCoordinates) {
        this.polygonCoordinates = polygonCoordinates;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }
}
