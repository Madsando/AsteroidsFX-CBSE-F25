import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.commonbullet.IBulletSPI;

module Enemy {
    requires CommonBullet;
    requires CommonCollision;
    requires Common;

    provides IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemy.EnemyProcessor;

    uses IBulletSPI;
}


