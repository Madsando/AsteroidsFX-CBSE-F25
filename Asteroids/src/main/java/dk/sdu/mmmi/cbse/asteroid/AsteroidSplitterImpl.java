package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        float size = e.getRadius() / 2;

        if (size < 5) { // Stop if the split asteroid is too small
            return;
        }

        for (int i = -1; i <= 1; i += 2) {
            Entity asteroid = new Asteroid();
            asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);

            asteroid.setRotation(e.getRotation() + i * 90);

            asteroid.setX(e.getX() + i * 2 * size);
            asteroid.setY(e.getY() + i * 2 * size);
            asteroid.setRadius(size);

            world.addEntity(asteroid);
        }
    }

}
