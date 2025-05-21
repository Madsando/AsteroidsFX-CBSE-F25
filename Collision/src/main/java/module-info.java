import dk.sdu.cbse.collision.CollisionDetectorSystem;
import dk.sdu.cbse.collision.CollisionHandlerSystem;
import dk.sdu.cbse.common.services.ISystemService;

module Collision {
    requires Common;
    requires CommonEntityComponents;
    requires CommonCollision;

    provides ISystemService with
            CollisionDetectorSystem,
            CollisionHandlerSystem;
}


