package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class EnemyProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(EEntityType.ENEMY)) {
            for (IEntityComponent entityComponent : enemy.getComponents()) {
                entityComponent.process(gameData, world, enemy);
            }
        }
    }
}
