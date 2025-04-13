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

        for (Entity e1 : world.getEntitiesWithComponent(CollisionCP.class)) {
            collidedEntities.add(e1.getID());

            for (Entity e2 : world.getEntitiesWithComponent(CollisionCP.class)) {
                // Make sure entities are different. Cannot collide with oneself
                // Make sure collisions with the selected entity has not been checked already
                if (e1.getID().equals(e2.getID()) | collidedEntities.contains(e2.getID())) {
                    continue;
                }

                if (entitiesCollide(e1, e2)) {
                    resolveCollision(e1, e2);
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
        source.getComponent(CollisionCP.class).addCollision(target.getEntityType());
        target.getComponent(CollisionCP.class).addCollision(source.getEntityType());
    }
}
