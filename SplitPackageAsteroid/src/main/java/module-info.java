import dk.sdu.cbse.common.services.IGamePluginService;

module SplitPackageAsteroid {
    requires Common;
    requires CommonAsteroid;
    requires CommonCollision;

    provides IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
}


