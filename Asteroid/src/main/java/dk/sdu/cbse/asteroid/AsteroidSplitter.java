package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.ICustomEntityBehaviour;
import dk.sdu.cbse.common.entitycomponents.*;

public class AsteroidSplitter implements ICustomEntityBehaviour {
    @Override
    public void process(GameData gameData, World world, Entity entity) {
        gameData.addScore(1);

        TransformCP transformCP = entity.getComponent(TransformCP.class);
        double x = transformCP.getX();
        double y = transformCP.getY();
        double rotation = transformCP.getRotation();
        double size = transformCP.getRadius() / 2;

        if (size < 5) { // Stop if the split asteroid is too small
            return;
        }

        for (int i = -1; i <= 1; i += 2) {
            Entity asteroid = new Entity(entity.getTypeID());

            double[] polygonCoordinates = {0.5, -1, 1.03, -0.33, 1.19, 0.45, 0.16, 1.1, -0.83, 0.89, -1.09, 0.38, -0.86, -0.76};
            for (int j = 0; j < polygonCoordinates.length; j++) {
                polygonCoordinates[j] *= size;
            }

            asteroid.addComponent(new ShapeCP(
                    polygonCoordinates,
                    new int[]{155, 155, 155}
            ));

            asteroid.addComponent(new TransformCP(
                    x + i * size,
                    y + i * size,
                    rotation + i * 90,
                    size
            ));

            asteroid.addComponent(new HealthCP(
                    1,
                    new AsteroidSplitter()
            ));

            asteroid.addComponent(new CollisionCP());

            asteroid.addComponent(new MovementCP(
                    1,
                    0,
                    false,
                    false,
                    true
            ));

            asteroid.addComponent(new WraparoundCP());

            world.addEntity(asteroid);
        }
    }
}
