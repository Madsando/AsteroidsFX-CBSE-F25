package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.CustomEntityBehaviour;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.asteroid.Asteroid;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.collision.ECollisionType;

public class AsteroidSplitter implements CustomEntityBehaviour {
    @Override
    public void process(GameData gameData, World world, Entity entity) {
        ShapeCP shape = entity.getComponent(ShapeCP.class);
        double size = shape.getRadius() / 2;

        PositionCP position = entity.getComponent(PositionCP.class);
        double x = position.getX();
        double y = position.getY();
        double rotation = position.getRotation();

        if (size < 5) { // Stop if the split asteroid is too small
            return;
        }

        for (int i = -1; i <= 1; i += 2) {
            Entity asteroid = new Asteroid();

            double[] polygonCoordinates = {0.5, -1, 1.03, -0.33, 1.19, 0.45, 0.16, 1.1, -0.83, 0.89, -1.09, 0.38, -0.86, -0.76};
            for (int j = 0; j < polygonCoordinates.length; j++) {
                polygonCoordinates[j] *= size;
            }

            asteroid.addComponent(new ShapeCP(
                    polygonCoordinates,
                    size,
                    new int[]{155, 155, 155}
            ));

            asteroid.addComponent(new PositionCP(
                    x + i * size,
                    y + i * size,
                    rotation + i * 90
            ));

            asteroid.addComponent(new HealthCP(
                    1,
                    new AsteroidSplitter()
            ));

            asteroid.addComponent(new CollisionCP(
                    ECollisionType.ASTEROID
            ));

            asteroid.addComponent(new MovementCP(
                    1,
                    0,
                    false,
                    false,
                    true,
                    false
            ));

            world.addEntity(asteroid);
        }
    }
}
