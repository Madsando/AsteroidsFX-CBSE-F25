import dk.sdu.cbse.collision.CollisionStrategy.ICollisionStrategy;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonCollision;
    requires java.desktop;

    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionDetector;

    provides ICollisionStrategy with dk.sdu.cbse.collision.CollisionStrategy.DestroyStrategy,
                                     dk.sdu.cbse.collision.CollisionStrategy.AsteroidStrategy,
                                     dk.sdu.cbse.collision.CollisionStrategy.EntityStrategy;
    uses ICollisionStrategy;
}


