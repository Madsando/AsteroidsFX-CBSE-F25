package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(EEntityType.ASTEROID)) {
            for (IEntityComponent entityComponent : asteroid.getComponents()) {
                entityComponent.process(gameData, world, asteroid);
            }
        }
    }
}
