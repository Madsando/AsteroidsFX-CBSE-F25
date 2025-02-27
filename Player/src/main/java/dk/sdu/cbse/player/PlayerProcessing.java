package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class PlayerProcessing implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double angle = Math.toRadians(player.getRotation());
                double changeX = Math.cos(angle);
                double changeY = Math.sin(angle);

                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 3);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 3);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                // TODO
            }
        }
    }
}
