package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.Objects;

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
                double xDistance = e.getX() - e2.getX();
                double yDistance = e.getY() - e2.getY();
                double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);

                if (distance < (e.getRadius() + e2.getRadius())) {
                    resolver.handleCollision(gameData, world, e, e2);
                }
            }
        }
    }
}
