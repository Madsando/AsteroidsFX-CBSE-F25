package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 3; i++) {
            Entity enemy = createEnemy(gameData);
            world.addEntity(enemy);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }

    private Entity createEnemy(GameData gameData) {
        Enemy enemy = new Enemy();

        double scalingFactor = 4.5;
        double[] polygonCoordinates = {1.28, 0, 1.25, -0.45, 1,-0.85,  0.55, -1.25, 0.15, -2.95, 2.5, -3, 2.6, -3.1, 2.5, -3.2, 0.15, -3.25,
                0.15, -3.25,-2.5, -3.2, -2.6, -3.1, -2.5, -3, -0.15, -2.95, -0.55, -1.25, -1, -0.85, -1.25, -0.45,
                -1.28,0, -1.25,  0.45, -1, 0.85, -0.55, 1.25, -0.15, 2.95, -2.5, 3, -2.6, 3.1, -2.5, 3.2, -0.15, 3.25,
                0.15, 3.25, 2.5, 3.2, 2.6, 3.1, 2.5, 3, 0.15, 2.95, 0.55, 1.25, 1, 0.85, 1.25, 0.45};
        double radius = 4;

        for (int i = 0; i < polygonCoordinates.length; i++) {
            polygonCoordinates[i] *= scalingFactor;
        }

        //double[] polygonCoordinates = {9, 0, -0.25,-2.7, 2, -6, -4.6, -4.4, -9,- 5.5, -9, 5.5, -4.6, 4.4, 2, 6, -0.25, 2.7};
        enemy.setPolygonCoordinates(polygonCoordinates);
        enemy.setColor(new int[]{254, 0, 0});

        enemy.setRadius((float) (radius * scalingFactor));
        enemy.setRotation(90);

        Random rng = new Random();
        //enemy.setX((double) gameData.getDisplayWidth() / 2 + rng.nextInt(-30, 30));
        //enemy.setY((double) gameData.getDisplayHeight() / 2 + rng.nextInt(-30, 30));
        enemy.setX(rng.nextInt(gameData.getDisplayWidth()));
        enemy.setY(rng.nextInt(gameData.getDisplayHeight()));

        enemy.setCooldown(75);
        enemy.setLastAttack(0);

        return enemy;
    }

}
