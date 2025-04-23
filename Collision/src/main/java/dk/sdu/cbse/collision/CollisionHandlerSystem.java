package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Queue;

public class CollisionHandlerSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void update(GameData gameData, World world) {
        for (Entity e : world.getEntitiesWithComponent(CollisionCP.class)) {
            CollisionCP collisionCP = e.getComponent(CollisionCP.class);
            Queue<EEntityType> collisions = collisionCP.getCollisions();

            while (!collisions.isEmpty()) {
                collisionCP.getCollisionBehaviour().process(gameData, world, e, collisions.poll());
            }
        }
    }
}
