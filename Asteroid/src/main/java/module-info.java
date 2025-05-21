import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonEntityComponents;
    requires CommonCollision;
    requires CommonMovement;

    uses IFeatureFlag;

    provides IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
}


