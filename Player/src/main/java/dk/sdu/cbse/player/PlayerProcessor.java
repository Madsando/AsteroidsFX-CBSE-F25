package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbullet.IBulletSPI;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerProcessor implements IEntityProcessingService {
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
                Player p = (Player) player;

                if (System.currentTimeMillis() - p.getLastAttack() > p.getCooldown()) {
                    p.setLastAttack(System.currentTimeMillis());

                    if (getIBulletSPI().stream().findFirst().isPresent()) {
                        getIBulletSPI().stream().findFirst().get().createBullet(world, player);
                    }
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
            }
        }
    }

    private Collection<? extends IBulletSPI> getIBulletSPI() {
        return ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
