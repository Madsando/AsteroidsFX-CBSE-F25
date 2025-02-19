package dk.sdu.mmmi.cbse.spaceship;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class SpaceshipProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity spaceship : world.getEntities(Spaceship.class)) {
            if (spaceship.hasCollided()) {
                spaceship.setHasCollided(false);
                world.removeEntity(spaceship);
                continue;
            }

            double changeX = Math.cos(Math.toRadians(spaceship.getRotation()));
            double changeY = Math.sin(Math.toRadians(spaceship.getRotation()));

            spaceship.setX(spaceship.getX() + changeX * 0.7);
            spaceship.setY(spaceship.getY() + changeY * 0.7);

            Random rnd = new Random();
            // Randomly rotate and move in another direction
            if (1 == rnd.nextInt(1, 120)) {
                double changeRotation = rnd.nextDouble(-75, 75);
                spaceship.setRotation(spaceship.getRotation() + changeRotation);
                break;
            }

            // Randomly shoot
            if (1 == rnd.nextInt(120)) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(spaceship, gameData));}
                );
            }

            // If Spaceship goes outside display, return them on the other side
            if (spaceship.getX() < 0) {
                spaceship.setX(spaceship.getX() - gameData.getDisplayWidth());
            }

            if (spaceship.getX() > gameData.getDisplayWidth()) {
                spaceship.setX(spaceship.getX() % gameData.getDisplayWidth());
            }

            if (spaceship.getY() < 0) {
                spaceship.setY(spaceship.getY() - gameData.getDisplayHeight());
            }

            if (spaceship.getY() > gameData.getDisplayHeight()) {
                spaceship.setY(spaceship.getY() % gameData.getDisplayHeight());
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
