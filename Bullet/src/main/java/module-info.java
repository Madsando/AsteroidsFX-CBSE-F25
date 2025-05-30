import dk.sdu.cbse.bullet.BulletInputControlSystem;
import dk.sdu.cbse.bullet.BulletSystem;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.bullet.IBulletSPI;
import dk.sdu.cbse.common.services.ISystemService;

module Bullet {
    requires Common;
    requires CommonEntityComponents;
    requires CommonBullet;
    requires CommonCollision;
    requires CommonInput;
    requires CommonMovement;

    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IBulletSPI with dk.sdu.cbse.bullet.BulletPlugin;
    provides ISystemService with
            BulletSystem,
            BulletInputControlSystem;

    uses IBulletSPI;
}


