package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.entitycomponents.MovementCP;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.entitycomponents.BulletCP;

import java.util.Random;

public class EnemyProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(EEntityType.ENEMY)) {
            Random rng = new Random();

            enemy.getComponent(HealthCP.class).process(gameData, world, enemy);

            MovementCP movementCP = enemy.getComponent(MovementCP.class);
            if (rng.nextInt(90) == 1) {
                movementCP.setRotationSpeed(rng.nextInt(-45, 45));
                movementCP.setLeft(true);
            }
            movementCP.process(gameData, world, enemy);
            movementCP.setLeft(false);

            if (enemy.getComponent(BulletCP.class) != null) {
                BulletCP bulletCP = enemy.getComponent(BulletCP.class);
                bulletCP.process(gameData, world, enemy);
            }
        }
    }
}
