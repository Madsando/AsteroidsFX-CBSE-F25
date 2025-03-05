package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Entity enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }

    private Entity createEnemy(GameData gameData) {
        Enemy enemy = new Enemy();

        double[] polygonCoordinates = {9, 0, -0.25,-2.7, 2, -6, -4.6, -4.4, -9,- 5.5, -9, 5.5, -4.6, 4.4, 2, 6, -0.25, 2.7};
        enemy.setPolygonCoordinates(polygonCoordinates);

        enemy.setRadius(9);
        enemy.setRotation(270);

        enemy.setX((double) gameData.getDisplayWidth() / 2 + 20);
        enemy.setY((double) gameData.getDisplayHeight() / 2 + 20);

        enemy.setCooldown(75);
        enemy.setLastAttack(0);

        return enemy;
    }

}
