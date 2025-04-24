package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.collision.ICollisionBehaviour;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.IEntityComponent;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CollisionCP implements IEntityComponent {
    private final ICollisionBehaviour collisionBehaviour;
    private final BlockingQueue<EEntityType> collisions;

    public CollisionCP(ICollisionBehaviour collisionBehaviour) {
        this.collisionBehaviour = collisionBehaviour;
        this.collisions = new ArrayBlockingQueue<>(3);
    }

    public void addCollision(EEntityType targetType) {
        collisions.offer(targetType); // Offer collision to queue. If the queue is full, it ignores it
    }

    public Queue<EEntityType> getCollisions() {
        return collisions;
    }

    public ICollisionBehaviour getCollisionBehaviour() {
        return collisionBehaviour;
    }
}
