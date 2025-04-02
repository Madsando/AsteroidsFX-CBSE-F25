package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.EntityComponent;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class BulletProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(EEntityType.BULLET)) {
            for (EntityComponent entityComponent : bullet.getComponents()) {
                entityComponent.process(gameData, world, bullet);
            }
        }
    }
}
