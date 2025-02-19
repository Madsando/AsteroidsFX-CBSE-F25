package dk.sdu.mmmi.cbse.spaceship;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class SpaceshipPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Entity spaceship = createSpaceship(gameData);
        world.addEntity(spaceship);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity spaceship : world.getEntities(Spaceship.class)) {
            world.removeEntity(spaceship);
        }
    }

    private Entity createSpaceship(GameData gameData) {
        Entity spaceship = new Spaceship();
        int size = 20;
        Random rnd = new Random();
        spaceship.setPolygonCoordinates(-5,-5,10,0,-5,5);
        spaceship.setX(300);
        spaceship.setY(300);
        spaceship.setRadius(size);
        spaceship.setRotation(rnd.nextInt(90));
        return spaceship;
    }
}
