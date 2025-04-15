import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.bullet.IBulletSPI;

module Bullet {
    requires Common;
    requires CommonEntityCP;
    requires CommonBullet;
    requires CommonCollision;

    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IBulletSPI with dk.sdu.cbse.bullet.BulletPlugin;
}


