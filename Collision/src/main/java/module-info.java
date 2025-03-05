import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.commonasteroid.SplitAsteroid;

module Collision {
    requires CommonAsteroid;
    requires CommonCollision;
    requires Common;
    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionDetector;
    uses SplitAsteroid;
}


