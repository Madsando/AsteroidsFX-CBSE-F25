package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonasteroid.Asteroid;
import dk.sdu.cbse.commonasteroid.SplitAsteroid;
import java.util.Collection;

import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class AsteroidProcessor implements IEntityProcessingService {
    private SplitAsteroid asteroidSplitter = getAsteroidSplitter();

    private SplitAsteroid getAsteroidSplitter() {
        Collection<? extends SplitAsteroid> AsteroidSplitCollection = ServiceLoader.load(SplitAsteroid.class).stream().map(ServiceLoader.Provider::get).collect(toList());

        if (AsteroidSplitCollection.stream().findFirst().isPresent()) {
            return AsteroidSplitCollection.stream().findFirst().get();
        }
        else {
            return null;
        }
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            if (asteroidSplitter != null & asteroid.isDead()) {
                asteroidSplitter.createSplitAsteroids(world, asteroid);
                world.removeEntity(asteroid);
                continue;
            }

            // MOVE ASTEROID
            double angle = Math.toRadians(asteroid.getRotation());
            double changeX = Math.cos(angle);
            double changeY = Math.sin(angle);

            asteroid.setX(asteroid.getX() + changeX);
            asteroid.setY(asteroid.getY() + changeY);

            // CHECK OUT OF BOUNDS
            if (asteroid.getX() < 0 - asteroid.getRadius()) {
                asteroid.setX(gameData.getDisplayWidth());
            } else if (asteroid.getX() > gameData.getDisplayWidth() + asteroid.getRadius()) {
                asteroid.setX(0);
            }

            if (asteroid.getY() < 0 - asteroid.getRadius()) {
                asteroid.setY(gameData.getDisplayHeight());
            } else if (asteroid.getY() > gameData.getDisplayHeight() + asteroid.getRadius()) {
                asteroid.setY(0);
            }
        }
    }
}
