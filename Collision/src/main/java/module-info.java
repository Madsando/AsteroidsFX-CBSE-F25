import dk.sdu.cbse.common.collision.ICollisionStrategy;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonEntityCP;
    requires CommonCollision;

    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionDetector;

    uses ICollisionStrategy;
}


