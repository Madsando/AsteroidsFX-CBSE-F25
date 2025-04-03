package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entity.EEntityType;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.stream.Collectors.toList;

public class EnemyPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        AtomicBoolean isFeatureEnabled = new AtomicBoolean(false);
        getFeatureFlagLoader().stream().findFirst().ifPresent(f -> {
            isFeatureEnabled.set(f.isFeatureEnabled("enemies"));});

        if (isFeatureEnabled.get()) {
            for (int i = 0; i < 3; i++) {
                Entity enemy = createEnemy(gameData);
                world.addEntity(enemy);
            }
        }

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(EEntityType.ENEMY)) {
            world.removeEntity(enemy);
        }
    }

    private Entity createEnemy(GameData gameData) {
        Entity enemy = new Entity(EEntityType.ENEMY);
        Random rng = new Random();

        double scalingFactor = 4.5;
        //double[] polygonCoordinates = {9, 0, -0.25,-2.7, 2, -6, -4.6, -4.4, -9,- 5.5, -9, 5.5, -4.6, 4.4, 2, 6, -0.25, 2.7};
        double[] polygonCoordinates = {1.28, 0, 1.25, -0.45, 1,-0.85,  0.55, -1.25, 0.15, -2.95, 2.5, -3, 2.6, -3.1, 2.5, -3.2, 0.15, -3.25,
                0.15, -3.25,-2.5, -3.2, -2.6, -3.1, -2.5, -3, -0.15, -2.95, -0.55, -1.25, -1, -0.85, -1.25, -0.45,
                -1.28,0, -1.25,  0.45, -1, 0.85, -0.55, 1.25, -0.15, 2.95, -2.5, 3, -2.6, 3.1, -2.5, 3.2, -0.15, 3.25,
                0.15, 3.25, 2.5, 3.2, 2.6, 3.1, 2.5, 3, 0.15, 2.95, 0.55, 1.25, 1, 0.85, 1.25, 0.45};
        double radius = 4 * scalingFactor;

        for (int i = 0; i < polygonCoordinates.length; i++) {
            polygonCoordinates[i] *= scalingFactor;
        }

        enemy.addComponent(new ShapeCP(
                polygonCoordinates,
                radius,
                new int[]{254, 0, 0}
        ));

        enemy.addComponent(new PositionCP(
                rng.nextInt(gameData.getDisplayWidth()),
                rng.nextInt(gameData.getDisplayHeight()),
                90
        ));

        enemy.addComponent(new HealthCP(
                2,
                null
        ));

        enemy.addComponent(new BulletCP(
                120,
                75,
                true
        ));

        enemy.addComponent(new MovementCP(
                0.75,
                3,
                false,
                false,
                true,
                false
        ));

        enemy.addComponent(new CollisionCP());

        return enemy;
    }

    public static Collection<? extends IFeatureFlag> getFeatureFlagLoader() {
        return ServiceLoader.load(IFeatureFlag.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
