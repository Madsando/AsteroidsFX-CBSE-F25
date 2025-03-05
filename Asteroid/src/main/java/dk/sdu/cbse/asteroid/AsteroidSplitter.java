package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commonasteroid.Asteroid;
import dk.sdu.cbse.commonasteroid.SplitAsteroid;

public class AsteroidSplitter implements SplitAsteroid {
    @Override
    public void createSplitAsteroids(World world, Entity e) {
        System.out.println("SPLITTING ASTEROID");
        System.out.println(world.getEntities(Asteroid.class).size());

        float size = e.getRadius() / 2;

        if (size < 3) { // Stop if the split asteroid is too small
            return;
        }

        for (int i = -1; i <= 1; i += 2) {
            Entity asteroid = new Asteroid();

            double[] polygonCoordinates = {0.5, -1, 1.03, -0.33, 1.19, 0.45, 0.16, 1.1, -0.83, 0.89, -1.09, 0.38, -0.86, -0.76};
            //double[] polygonCoordinates = {0.5, -1, 1.03, -0.33, 1.19, 0.45, 0.16, 1.1, -0.83, 0.89, -1.09, 0.38, -0.86, -0.76};
            for (int j = 0; j < polygonCoordinates.length; j++) {
                polygonCoordinates[j] *= size;
            }

            asteroid.setPolygonCoordinates(polygonCoordinates);
            asteroid.setColor(new int[]{0,0,0});
            asteroid.setRotation(e.getRotation() + i * 90);

            asteroid.setX(e.getX() + i * 2 * size);
            asteroid.setY(e.getY() + i * 2 * size);
            asteroid.setRadius(size);

            world.addEntity(asteroid);
        }
    }
}
