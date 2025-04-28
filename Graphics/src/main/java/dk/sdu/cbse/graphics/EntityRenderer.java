package dk.sdu.cbse.graphics;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityRenderer implements IGraphicsComponent {
    private Pane entityPane;
    private Map<Entity, Polygon> polygons;

    @Override
    public Node createComponent(GameData gameData, World world) {
        entityPane = new Pane();
        polygons = new ConcurrentHashMap<>();

        for (Entity entity : world.getEntities()) {
            ShapeCP shapeCP = entity.getComponent(ShapeCP.class);
            Polygon polygon = new Polygon(shapeCP.getPolygonCoordinates());
            int[] rbgValues = shapeCP.getColor();
            polygon.setFill(Color.rgb(rbgValues[0] % 256, rbgValues[1] % 256, rbgValues[2] % 256));
            polygons.put(entity, polygon);
            entityPane.getChildren().add(polygon);
        }

        return entityPane;
    }

    @Override
    public void updateComponent(GameData gameData, World world) {
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                entityPane.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                ShapeCP shapeCP = entity.getComponent(ShapeCP.class);
                polygon = new Polygon(shapeCP.getPolygonCoordinates());
                int[] rbgValues = shapeCP.getColor();
                polygon.setFill(Color.rgb(rbgValues[0] % 256, rbgValues[1] % 256, rbgValues[2] % 256));
                polygons.put(entity, polygon);
                entityPane.getChildren().add(polygon);
            }

            // Change color
            ShapeCP shapeCP = entity.getComponent(ShapeCP.class);
            int[] rbgValues = shapeCP.getColor();
            polygon.setFill(Color.rgb(rbgValues[0] % 256, rbgValues[1] % 256, rbgValues[2] % 256));

            // Change position of rendered polygon
            TransformCP transformCP = entity.getComponent(TransformCP.class);
            polygon.setTranslateX(transformCP.getX());
            polygon.setTranslateY(transformCP.getY());
            polygon.setRotate(transformCP.getRotation());
        }
    }
}
