package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.collision.CollisionCP;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.PositionCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.utility.UnorderedPair;

import java.util.ArrayList;

public class CollisionDetector implements IPostEntityProcessingService {
    private final CollisionResolver resolver = new CollisionResolver();
    private final ArrayList<UnorderedPair<Entity>> collidingEntityPairs = new ArrayList<>();
    private final Grid grid = new Grid(new GameData(), 45);

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
                    resolver.resolveCollision(gameData, world, collidingEntityPair);
                }
            }
        }
    }
}
