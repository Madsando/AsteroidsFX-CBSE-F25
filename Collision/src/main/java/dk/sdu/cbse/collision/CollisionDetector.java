package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.PositionCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.utility.UnorderedPair;

import java.util.ArrayList;

public class CollisionDetector implements IPostEntityProcessingService {
    private final ArrayList<UnorderedPair<Entity>> collidingEntityPairs = new ArrayList<>();

    @Override
    public void process(GameData gameData, World world) {
        collidingEntityPairs.clear();

        for (Entity e : world.getEntities()) {
            if (e.getComponent(CollisionCP.class) == null) {
                continue;
            }

            for (Entity e2 : world.getEntities()) {
                if (e2.getComponent(CollisionCP.class) == null) {
                    continue;
                }

                // Make sure entities are different. Cannot collide with oneself
                if (e.getID().equals(e2.getID())) {
                    continue;
                }

                // Using Pythagoras' distance formula
                PositionCP ePositionCP = e.getComponent(PositionCP.class);
                PositionCP e2PositionCP = e2.getComponent(PositionCP.class);

                double xDistance = ePositionCP.getX() - e2PositionCP.getX();
                double yDistance = ePositionCP.getY() - e2PositionCP.getY();
                double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);

                ShapeCP eShape = e.getComponent(ShapeCP.class);
                ShapeCP e2Shape = e2.getComponent(ShapeCP.class);

                if (distance <= (eShape.getRadius() + e2Shape.getRadius())) {
                    UnorderedPair<Entity> collidingEntityPair = new UnorderedPair<>(e, e2);

                    if (collidingEntityPairs.contains(collidingEntityPair)) {
                        continue;
                    }

                    collidingEntityPairs.add(collidingEntityPair);
                    this.resolveCollision(collidingEntityPair);
                }
            }
        }
    }

    private void resolveCollision(UnorderedPair<Entity> entityPair) {
        CollisionCP e1CollisionCP = entityPair.getK().getComponent(CollisionCP.class);
        e1CollisionCP.setTargetType(entityPair.getV().getEntityType());
        e1CollisionCP.setHasCollided(true);

        CollisionCP e2CollisionCP = entityPair.getV().getComponent(CollisionCP.class);
        e2CollisionCP.setTargetType(entityPair.getK().getEntityType());
        e2CollisionCP.setHasCollided(true);
    }
}
