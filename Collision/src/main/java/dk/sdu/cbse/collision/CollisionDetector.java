package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.PositionCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollisionDetector implements IPostEntityProcessingService {
    private final Set<String> collidedEntities = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void process(GameData gameData, World world) {
        collidedEntities.clear();

        for (Entity e : world.getEntities()) {
            if (e.getComponent(CollisionCP.class) == null) {
                continue;
            }

            collidedEntities.add(e.getID());

            for (Entity e2 : world.getEntities()) {
                // Make sure entities are different. Cannot collide with oneself
                // Check that they can collide e.g. have CollisionCP
                if (e2.getComponent(CollisionCP.class) == null | e.getID().equals(e2.getID())) {
                    continue;
                }

                if (collidedEntities.contains(e2.getID())) {
                    continue;
                }

                if (entitiesCollide(e, e2)) {
                    resolveCollision(e, e2);
                }
            }
        }
    }

    private Boolean entitiesCollide(Entity e1, Entity e2) {
        // Using Pythagoras' distance formula
        PositionCP ePositionCP = e1.getComponent(PositionCP.class);
        PositionCP e2PositionCP = e2.getComponent(PositionCP.class);

        double xDistance = ePositionCP.getX() - e2PositionCP.getX();
        double yDistance = ePositionCP.getY() - e2PositionCP.getY();
        double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);

        ShapeCP eShape = e1.getComponent(ShapeCP.class);
        ShapeCP e2Shape = e2.getComponent(ShapeCP.class);

        return distance <= (eShape.getRadius() + e2Shape.getRadius());
    }

    private void resolveCollision(Entity source, Entity target) {
        CollisionCP sourceCollisionCP = source.getComponent(CollisionCP.class);
        sourceCollisionCP.setTargetType(target.getEntityType());
        sourceCollisionCP.setHasCollided(true);

        CollisionCP targetCollisionCP = target.getComponent(CollisionCP.class);
        targetCollisionCP.setTargetType(source.getEntityType());
        targetCollisionCP.setHasCollided(true);
    }
}
