package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbullet.Bullet;

public class BulletProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            if (bullet.isDead()) {
                world.removeEntity(bullet);
                continue;
            }

            // MOVE BULLET
            double angle = Math.toRadians(bullet.getRotation());
            double changeX = Math.cos(angle);
            double changeY = Math.sin(angle);

            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);

            // CULL IF OUT OF BOUNDS
            if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() ||
                bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }
        }
    }
}
