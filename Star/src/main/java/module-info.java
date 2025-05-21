import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.ISystemService;
import dk.sdu.cbse.star.FlickeringStarSystem;
import dk.sdu.cbse.star.StarPlugin;

module Star {
    requires Common;
    requires CommonEntityComponents;

    uses IFeatureFlag;

    provides IGamePluginService with StarPlugin;
    provides ISystemService with FlickeringStarSystem;
}


