import dk.sdu.cbse.common.score.ScoreService;
import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonEntityComponents;
    requires CommonCollision;
    requires CommonMovement;
    requires CommonScore;

    uses IFeatureFlag;
    uses ScoreService;

    provides IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
}


