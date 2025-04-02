import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.bullet.IBulletSPI;

module Bullet {
    requires Common;
    requires CommonEntityCP;
    requires CommonBullet;

    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletProcessor;
    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IBulletSPI with dk.sdu.cbse.bullet.BulletPlugin;
}


