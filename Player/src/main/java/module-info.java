import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;

module Player {
    requires Common;
    requires CommonEntityCP;
    requires CommonCollision;

    uses IFeatureFlag;

    provides IGamePluginService with dk.sdu.cbse.player.PlayerPlugin;
}


