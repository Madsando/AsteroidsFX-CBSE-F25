package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.services.IEntityComponent;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CollisionCP implements IEntityComponent {
    private final BlockingQueue<Entity> collisions;

    public CollisionCP() {
        this.collisions = new ArrayBlockingQueue<>(3);
    }

    public void addCollision(Entity entity) {
        collisions.offer(entity); // Offer collision to queue. If the queue is full, it ignores it
    }

    public Queue<Entity> getCollisions() {
        return collisions;
    }
}
