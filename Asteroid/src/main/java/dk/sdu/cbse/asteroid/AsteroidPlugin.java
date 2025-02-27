package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 100; i++) {
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

        asteroid.setPolygonCoordinates(5, 5, 5, -5, -5, -5, -5, 5);
        asteroid.setRadius(5);

        Random rng = new Random();
        asteroid.setRotation(rng.nextInt(360));
        asteroid.setX(rng.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(rng.nextInt(gameData.getDisplayHeight()));

        return asteroid;
    }

}
