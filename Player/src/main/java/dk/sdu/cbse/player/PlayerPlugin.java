package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Entity player = createPlayer(gameData);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            world.removeEntity(player);
        }
    }

    private Entity createPlayer(GameData gameData) {
        Entity player = new Player();

        player.setPolygonCoordinates(18, 0, 8.75,-2.7, 11, -6, 4.4, -4.4, 0,- 5.5, 0, 5.5, 4.4, 4.4, 11, 6, 8.75, 2.7);

        player.setRadius(10);
        player.setRotation(270);

        player.setX((double) gameData.getDisplayWidth() / 2);
        player.setY((double) gameData.getDisplayHeight() / 2);

        return player;
    }

}
