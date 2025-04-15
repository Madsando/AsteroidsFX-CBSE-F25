package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class PlayerProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(EEntityType.PLAYER)) {
            for (IEntityComponent entityComponent : player.getComponents()) {
                entityComponent.process(gameData, world, player);
            }
        }
    }
}
