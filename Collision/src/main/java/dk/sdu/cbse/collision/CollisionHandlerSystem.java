package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entitycomponents.CollisionIgnoreSelfCP;
import dk.sdu.cbse.common.entitycomponents.DamageCP;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
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
            Queue<Entity> collisions = collisionCP.getCollisions();

            while (!collisions.isEmpty()) {
                Entity collidingEntity = collisions.poll();

                CollisionIgnoreSelfCP entityCollisionIgnoreSelfCP = e.getComponent(CollisionIgnoreSelfCP.class);

                if (entityCollisionIgnoreSelfCP != null) {
                    if (e.getTypeID() == collidingEntity.getTypeID()) {
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
