import dk.sdu.cbse.bullet.BulletInputControlSystem;
import dk.sdu.cbse.bullet.BulletSystem;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.bullet.IBulletService;
import dk.sdu.cbse.common.services.ISystemService;

module Bullet {
    requires Common;
    requires CommonEntityComponents;
    requires CommonBullet;
    requires CommonCollision;
    requires CommonMovement;

    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IBulletService with dk.sdu.cbse.bullet.BulletPlugin;
    provides ISystemService with
            BulletSystem,
            BulletInputControlSystem;

    uses IBulletService;
}


