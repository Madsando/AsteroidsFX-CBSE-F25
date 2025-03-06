import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonCollision;
    requires java.desktop;

    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionDetector;
}


