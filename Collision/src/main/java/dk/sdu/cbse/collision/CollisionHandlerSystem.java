package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.collision.CollisionCP;
import dk.sdu.cbse.common.collision.CollisionIgnoreSelfCP;
import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;
import java.util.Queue;

public class CollisionHandlerSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{CollisionCP.class},
                new Class[]{CollisionIgnoreSelfCP.class, HealthCP.class}
        );
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        for (Node n : nodes) {
            CollisionCP collisionCP = (CollisionCP) n.getComponent(CollisionCP.class);
            Queue<String> collisions = collisionCP.getCollisions();

            while (!collisions.isEmpty()) {
                Entity collidedEntity = world.getEntity(collisions.poll());
                if (collidedEntity == null) {
                    continue;
                }

                if (n.getOptionalComponent(CollisionIgnoreSelfCP.class).isPresent()) {
                    if (n.getTypeID() == collidedEntity.getTypeID()) {
                        continue;
                    }
                }

                DamageCP damageCP = collidedEntity.getComponent(DamageCP.class);
                if (damageCP != null & n.getOptionalComponent(HealthCP.class).isPresent()) {
                    HealthCP healthCP = (HealthCP) n.getOptionalComponent(HealthCP.class).get();
                    healthCP.subtractHealth(damageCP.getDamage());
                }
            }
        }
    }
}
