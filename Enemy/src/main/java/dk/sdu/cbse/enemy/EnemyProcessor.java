package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbullet.IBulletSPI;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            double angle = Math.toRadians(enemy.getRotation());
            double changeX = Math.cos(angle);
            double changeY = Math.sin(angle);

            enemy.setX(enemy.getX() + changeX * 0.75);
            enemy.setY(enemy.getY() + changeY * 0.75);

            Random rng = new Random();
            if (rng.nextInt(90) == 1) {
                enemy.setRotation(rng.nextInt(-45, 45));
            }
            if (rng.nextInt(120) == 1) {
                Enemy p = (Enemy) enemy;

                if (System.currentTimeMillis() - p.getLastAttack() > p.getCooldown()) {
                    p.setLastAttack(System.currentTimeMillis());

                    if (getIBulletSPI().stream().findFirst().isPresent()) {
                        getIBulletSPI().stream().findFirst().get().createBullet(world, enemy);
                    }
                }
            }

            // CHECK OUT OF BOUNDS
            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            } else if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(0);
            }

            if (enemy.getY() < 0) {
                enemy.setY(gameData.getDisplayHeight());
            } else if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(0);
            }
        }
    }

    private Collection<? extends IBulletSPI> getIBulletSPI() {
        return ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
