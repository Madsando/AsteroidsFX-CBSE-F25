package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbulletcp.BulletCP;

public class PlayerProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {

            player.getComponent(HealthCP.class).process(gameData, world, player);

            MovementCP movementCP = player.getComponent(MovementCP.class);
            movementCP.setForward(gameData.getInputs().isDown(EGameInputs.FORWARD));
            movementCP.setLeft(gameData.getInputs().isDown(EGameInputs.LEFT));
            movementCP.setRight(gameData.getInputs().isDown(EGameInputs.RIGHT));
            movementCP.process(gameData, world, player);

            if (player.getComponent(BulletCP.class) != null) {
                BulletCP bulletCP = player.getComponent(BulletCP.class);
                bulletCP.setShouldAttack(gameData.getInputs().isDown(EGameInputs.ACTION));
                bulletCP.process(gameData, world, player);
            }

            /*
            // PROCESS PLAYER INPUT
            if (gameData.getInputs().isDown(EGameInputs.FORWARD)) {
                double angle = Math.toRadians(player.getRotation());
                double changeX = Math.cos(angle);
                double changeY = Math.sin(angle);

                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if (gameData.getInputs().isDown(EGameInputs.LEFT)) {
                player.setRotation(player.getRotation() - 3);
            }
            if (gameData.getInputs().isDown(EGameInputs.RIGHT)) {
                player.setRotation(player.getRotation() + 3);
            }
            if (gameData.getInputs().isDown(EGameInputs.ACTION)) {
                Player p = (Player) player;

                if (System.currentTimeMillis() - p.getLastAttack() > p.getCooldown()) {
                    p.setLastAttack(System.currentTimeMillis());

                    getIBulletSPI().stream().findFirst().ifPresent(
                            spi -> world.addEntity(spi.createBullet(player))
                    );
                }
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
            }*/
        }
    }
}
