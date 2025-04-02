package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.entitycomponents.BulletCP;

public class PlayerProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(EEntityType.PLAYER)) {

            player.getComponent(HealthCP.class).process(gameData, world, player);

            MovementCP movementCP = player.getComponent(MovementCP.class);
            movementCP.setForward(gameData.getInputs().isDown(EGameInputs.FORWARD));
            movementCP.setLeft(gameData.getInputs().isDown(EGameInputs.LEFT));
            movementCP.setRight(gameData.getInputs().isDown(EGameInputs.RIGHT));
            movementCP.process(gameData, world, player);

            if (player.getComponent(BulletCP.class) != null) {
                BulletCP bulletCP = player.getComponent(BulletCP.class);
                bulletCP.setShouldAttack(gameData.getInputs().isDown(EGameInputs.ACTION));
                bulletCP.process(gameData, world, player);
            }
        }
    }
}
