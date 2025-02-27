package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class PlayerProcessing implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            // PROCESS PLAYER INPUT
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

            // CHECK OUT OF BOUNDS
            if (player.getX() < 0) {
                player.setX(gameData.getDisplayWidth());
            } else if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(0);
            }

            if (player.getY() < 0) {
                player.setY(gameData.getDisplayHeight());
            } else if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(0);
            }
        }
    }
}
