import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonEntityComponents;
    requires CommonCollision;
    requires CommonBullet;
    requires CommonMovement;

    uses IFeatureFlag;

    provides IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
}


