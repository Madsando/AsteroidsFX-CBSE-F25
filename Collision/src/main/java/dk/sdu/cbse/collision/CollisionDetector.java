package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.PositionCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.commoncollision.CollisionPair;

public class CollisionDetector implements IPostEntityProcessingService {
    private final CollisionResolver resolver = new CollisionResolver();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
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
                    resolver.resolveCollision(gameData, world, new CollisionPair<>(e, e2));
                }
            }
        }
    }
}
