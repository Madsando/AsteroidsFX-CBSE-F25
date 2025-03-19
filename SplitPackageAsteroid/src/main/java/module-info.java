import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IEntityProcessingService;

module SplitPackageAsteroid {
    requires Common;
    requires CommonAsteroid;
    requires CommonCollision;

    provides IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroid.AsteroidProcessor;
}


