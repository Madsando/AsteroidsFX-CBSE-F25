package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.bullet.IBulletSPI;
import dk.sdu.cbse.common.entity.EEntityType;

public class BulletPlugin implements IGamePluginService, IBulletSPI {
    @Override
    public void start(GameData gameData, World world) {
        // No bullets should be spawned at the start
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(EEntityType.BULLET)) {
            world.removeEntity(bullet);
        }
    }

    @Override
    public Entity createBullet(Entity shooter) {
        Entity bullet = new Entity(EEntityType.BULLET);

        TransformCP shooterTransformCP = shooter.getComponent(TransformCP.class);
        double shooterX = shooterTransformCP.getX();
        double shooterY = shooterTransformCP.getY();
        double shooterRotation = shooterTransformCP.getRotation();
        double shooterRadius = shooterTransformCP.getRadius();

        int bulletRadius = 2;
        double changeX = Math.cos(Math.toRadians(shooterRotation));
        double changeY = Math.sin(Math.toRadians(shooterRotation));
        double bulletX = shooterX + changeX * (shooterRadius + bulletRadius + 2);
        double bulletY = shooterY + changeY * (shooterRadius + bulletRadius + 2);

        bullet.addComponent(new ShapeCP(
                new double[]{2, -1, 2, 1, -1, 1, -1, -1},
                new int[]{0, 254, 34}
        ));

        bullet.addComponent(new HealthCP(
                1,
                null
        ));

        bullet.addComponent(new TransformCP(
                bulletX,
                bulletY,
                shooterRotation,
                bulletRadius
        ));

        bullet.addComponent(new MovementCP(
                3,
                0,
                false,
                false,
                true
        ));

        bullet.addComponent(new DamageCP(1));

        bullet.addComponent(new CullingCP());

        bullet.addComponent(new CollisionCP());

        return bullet;
    }

}
