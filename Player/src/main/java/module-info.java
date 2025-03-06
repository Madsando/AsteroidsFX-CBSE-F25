import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.commonbullet.IBulletSPI;

module Player {
    requires Common;
    requires CommonBullet;

    provides IGamePluginService with dk.sdu.cbse.player.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.player.PlayerProcessor;

    uses IBulletSPI;
}


