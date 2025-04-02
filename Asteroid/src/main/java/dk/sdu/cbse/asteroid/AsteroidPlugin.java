package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.entitycomponents.PositionCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entity.ECollisionType;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 25; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        Random rng = new Random();

        // The polygon-coordinates describe a shape that rather closely follows a circle with radius 1.
        double[] polygonCoordinates = {0.5, -1, 1.03, -0.33, 1.19, 0.45, 0.16, 1.1, -0.83, 0.89, -1.09, 0.38, -0.86, -0.76};
        int scalingFactor = rng.nextInt(10) + 5;
        for (int i = 0; i < polygonCoordinates.length; i++) {
            polygonCoordinates[i] *= scalingFactor;
        }

        asteroid.addComponent(new ShapeCP(
                polygonCoordinates,
                scalingFactor,
                new int[]{120, 120, 120}
        ));

        int x = rng.nextInt(gameData.getDisplayWidth());
        while (x > (gameData.getDisplayWidth() / 2) - 50 & x < (gameData.getDisplayWidth() / 2) + 50) {
            x = rng.nextInt(gameData.getDisplayWidth());
        }

        int y = rng.nextInt(gameData.getDisplayHeight());
        while (y > (gameData.getDisplayHeight() / 2) - 50 & y < (gameData.getDisplayHeight() / 2) + 50) {
            y = rng.nextInt(gameData.getDisplayHeight());
        }

        asteroid.addComponent(new PositionCP(
                x,
                y,
                rng.nextInt(360)
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

        return asteroid;
    }

}
