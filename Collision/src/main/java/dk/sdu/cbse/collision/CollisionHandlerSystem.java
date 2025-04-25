package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entitycomponents.CollisionIgnoreCP;
import dk.sdu.cbse.common.entitycomponents.DamageCP;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Arrays;
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
            Queue<Entity> collisions = collisionCP.getCollisions();

            while (!collisions.isEmpty()) {
                Entity collidingEntity = collisions.poll();

                CollisionIgnoreCP entityCollisionIgnoreCP = e.getComponent(CollisionIgnoreCP.class);
                CollisionIgnoreCP collidingEntityCollisionIgnoreCP = collidingEntity.getComponent(CollisionIgnoreCP.class);

                if (collidingEntityCollisionIgnoreCP != null & entityCollisionIgnoreCP != null) {
                    if (Arrays.stream(entityCollisionIgnoreCP.getIgnoreCollisionsWithIds()).anyMatch(id -> id == collidingEntityCollisionIgnoreCP.getId())) {
                        continue;
                    }
                }

                DamageCP damageCP = collidingEntity.getComponent(DamageCP.class);
                HealthCP healthCP = e.getComponent(HealthCP.class);

                if (damageCP != null & healthCP != null) {
                    healthCP.subtractHealth(damageCP.getDamage());
                }
            }
        }
    }
}
