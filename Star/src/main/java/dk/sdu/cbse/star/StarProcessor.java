package dk.sdu.cbse.star;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class StarProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity star : world.getEntities(EEntityType.OTHER)) {
            for (IEntityComponent entityComponent : star.getComponents()) {
                entityComponent.process(gameData, world, star);
            }
        }
    }
}
