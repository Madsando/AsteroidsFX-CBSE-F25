package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.entitycomponents.PositionCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.bullet.IBulletSPI;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.collision.ECollisionType;

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
    public Entity createBullet(Entity shooter) {
        Entity bullet = new Bullet();

        PositionCP shooterPositionCP = shooter.getComponent(PositionCP.class);
        double shooterX = shooterPositionCP.getX();
        double shooterY = shooterPositionCP.getY();
        double shooterRotation = shooterPositionCP.getRotation();

        ShapeCP shooterShapeCP = shooter.getComponent(ShapeCP.class);
        double shooterRadius = shooterShapeCP.getRadius();

        int bulletRadius = 2;
        double changeX = Math.cos(Math.toRadians(shooterRotation));
        double changeY = Math.sin(Math.toRadians(shooterRotation));
        double bulletX = shooterX + changeX * (shooterRadius + bulletRadius);
        double bulletY = shooterY + changeY * (shooterRadius + bulletRadius);

        bullet.addComponent(new ShapeCP(
                new double[]{2, -1, 2, 1, -1, 1, -1, -1},
                bulletRadius,
                new int[]{0, 254, 34}
        ));

        bullet.addComponent(new HealthCP(
                1,
                null
        ));

        bullet.addComponent(new PositionCP(
                bulletX,
                bulletY,
                shooterRotation

        ));

        bullet.addComponent(new MovementCP(
                3,
                0,
                false,
                false,
                true,
                true

        ));

        bullet.addComponent(new CollisionCP(
                ECollisionType.BULLET
        ));

        return bullet;
    }

}
