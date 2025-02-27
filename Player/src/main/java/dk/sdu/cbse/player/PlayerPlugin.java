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
        Player player = new Player();

        double[] polygonCoordinates = {9, 0, -0.25,-2.7, 2, -6, -4.6, -4.4, -9,- 5.5, -9, 5.5, -4.6, 4.4, 2, 6, -0.25, 2.7};
        player.setPolygonCoordinates(polygonCoordinates);

        player.setRadius(9);
        player.setRotation(270);

        player.setX((double) gameData.getDisplayWidth() / 2);
        player.setY((double) gameData.getDisplayHeight() / 2);

        player.setCooldown(75);
        player.setLastAttack(0);

        return player;
    }

}
