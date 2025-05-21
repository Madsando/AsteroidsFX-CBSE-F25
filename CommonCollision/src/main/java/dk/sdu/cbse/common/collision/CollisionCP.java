package dk.sdu.cbse.common.collision;

import dk.sdu.cbse.common.services.IEntityComponent;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CollisionCP implements IEntityComponent {
    private final BlockingQueue<String> collisions;

    public CollisionCP() {
        this.collisions = new ArrayBlockingQueue<>(3);
    }

    public void addCollision(String entityID) {
        collisions.offer(entityID); // Offer collision to queue. If the queue is full, it ignores it
    }

    public Queue<String> getCollisions() {
        return collisions;
    }
}
