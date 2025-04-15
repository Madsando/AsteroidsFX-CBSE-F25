import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.star.StarPlugin;

module Star {
    requires Common;
    requires CommonEntityCP;

    uses IFeatureFlag;

    provides IGamePluginService with StarPlugin;
}


