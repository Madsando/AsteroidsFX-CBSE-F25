package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 50; i++) {
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

        asteroid.setPolygonCoordinates(polygonCoordinates);
        asteroid.setRadius(scalingFactor);

        asteroid.setRotation(rng.nextInt(360));
        asteroid.setX(rng.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(rng.nextInt(gameData.getDisplayHeight()));

        return asteroid;
    }

}
