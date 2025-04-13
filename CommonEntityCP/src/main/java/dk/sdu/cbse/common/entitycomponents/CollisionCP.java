package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.collision.ICollisionBehaviour;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EntityComponent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CollisionCP implements EntityComponent {
    private final ICollisionBehaviour collisionBehaviour;
    private final Queue<EEntityType> collisions;

    public CollisionCP(ICollisionBehaviour collisionBehaviour) {
        this.collisionBehaviour = collisionBehaviour;
        this.collisions = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        while (!collisions.isEmpty()) {
            collisionBehaviour.process(gameData, world, entity, collisions.poll());
        }
    }

    @Override
    public int getPriority() {
        return 1;
    }

    public void addCollision(EEntityType targetType) {
        collisions.add(targetType);
    }
}
