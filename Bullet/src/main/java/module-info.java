import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.commonbullet.IBulletSPI;

module Bullet {
    requires Common;
    requires CommonBullet;
    requires CommonCollision;

    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletProcessor;
    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IBulletSPI with dk.sdu.cbse.bullet.BulletPlugin;
}


