package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.commonbullet.Bullet;
import dk.sdu.cbse.commonbullet.IBulletSPI;

public class BulletPlugin implements IGamePluginService, IBulletSPI {
    @Override
    public void start(GameData gameData, World world) {
        return; // No bullets should be spawned at the start
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            world.removeEntity(bullet);
        }
    }

    @Override
    public void createBullet(World world, Entity shooter) {
        Entity bullet = new Bullet();

        bullet.setColor(new int[]{0, 254, 34});
        bullet.setPolygonCoordinates(2, -1, 2, 1, -1, 1, -1, -1);
        bullet.setRadius(2);
        bullet.setRotation(shooter.getRotation());

        bullet.setHealth(1);

        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * (shooter.getRadius() + bullet.getRadius()));
        bullet.setY(shooter.getY() + changeY * (shooter.getRadius() + bullet.getRadius()));

        world.addEntity(bullet);
    }

}
