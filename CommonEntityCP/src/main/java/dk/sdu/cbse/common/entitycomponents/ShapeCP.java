package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.services.IEntityComponent;

public class ShapeCP implements IEntityComponent {
    private double[] polygonCoordinates;
    private int[] color;

    public ShapeCP(double[] polygonCoordinates, int[] color) {
        this.polygonCoordinates = polygonCoordinates;
        this.color = color;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setPolygonCoordinates(double[] polygonCoordinates) {
        this.polygonCoordinates = polygonCoordinates;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }
}
