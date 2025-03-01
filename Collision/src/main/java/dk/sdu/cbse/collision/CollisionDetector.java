package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.commoncollision.ICollidableEntity;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                //TODO: Detect collision
            }
        }
    }
}
