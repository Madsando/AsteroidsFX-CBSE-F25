package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.*;

import java.util.Random;

public class AsteroidFactory {
    public static Entity createAsteroid(int typeID, double x, double y, double rotation, double size) {
        Entity asteroid = new Entity(typeID);

        // The polygon-coordinates describe a shape that rather closely follows a circle with radius 1.
        double[] polygonCoordinates = {0.5, -1, 1.03, -0.33, 1.19, 0.45, 0.16, 1.1, -0.83, 0.89, -1.09, 0.38, -0.86, -0.76};
        for (int i = 0; i < polygonCoordinates.length; i++) {
            polygonCoordinates[i] *= size;
        }

        asteroid.addComponent(new ShapeCP(
                polygonCoordinates,
                new int[]{120, 120, 120}
        ));

        asteroid.addComponent(new TransformCP(
                x,
                y,
                rotation,
                size
        ));

        asteroid.addComponent(new HealthCP(
                1,
                new AsteroidSplitter()
        ));

        asteroid.addComponent(new CollisionCP());

        asteroid.addComponent(new CollisionIgnoreSelfCP());

        asteroid.addComponent(new DamageCP(Integer.MAX_VALUE));

        asteroid.addComponent(new MovementCP(
                1,
                0,
                false,
                false,
                true
        ));

        asteroid.addComponent(new WraparoundCP());

        return asteroid;
    }
}
