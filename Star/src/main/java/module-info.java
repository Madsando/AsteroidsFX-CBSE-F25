import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.star.StarPlugin;
import dk.sdu.cbse.star.StarProcessor;

module Star {
    requires Common;
    requires CommonEntityCP;

    uses IFeatureFlag;

    provides IGamePluginService with StarPlugin;
    provides IEntityProcessingService with StarProcessor;
}


