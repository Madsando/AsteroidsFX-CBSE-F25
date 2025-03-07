import dk.sdu.cbse.commoncollision.ICollisionStrategy;

module CollisionStrategies {
    requires Common;
    requires CommonCollision;

    provides ICollisionStrategy with dk.sdu.cbse.collisionstrategies.AsteroidStrategy,
                                     dk.sdu.cbse.collisionstrategies.DestroyStrategy,
                                     dk.sdu.cbse.collisionstrategies.EntityStrategy;
}


