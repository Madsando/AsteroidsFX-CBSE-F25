package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.collision.ICollisionBehaviour;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.IEntityComponent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CollisionCP implements IEntityComponent {
    private final ICollisionBehaviour collisionBehaviour;
    private final Queue<EEntityType> collisions;

    public CollisionCP(ICollisionBehaviour collisionBehaviour) {
        this.collisionBehaviour = collisionBehaviour;
        this.collisions = new ConcurrentLinkedQueue<>();
    }

    public void addCollision(EEntityType targetType) {
        collisions.add(targetType);
    }

    public Queue<EEntityType> getCollisions() {
        return collisions;
    }

    public ICollisionBehaviour getCollisionBehaviour() {
        return collisionBehaviour;
    }
}
