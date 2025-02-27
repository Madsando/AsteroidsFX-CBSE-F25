package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            // MOVE ASTEROID
            double angle = Math.toRadians(asteroid.getRotation());
            double changeX = Math.cos(angle);
            double changeY = Math.sin(angle);

            asteroid.setX(asteroid.getX() + changeX);
            asteroid.setY(asteroid.getY() + changeY);

            // CHECK OUT OF BOUNDS
            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            } else if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(0);
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            } else if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(0);
            }
        }
    }
}
