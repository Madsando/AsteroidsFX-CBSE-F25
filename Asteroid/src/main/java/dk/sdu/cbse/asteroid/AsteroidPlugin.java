package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.stream.Collectors.toList;

public class AsteroidPlugin implements IGamePluginService {
    private static int typeId = 0;

    @Override
    public void start(GameData gameData, World world) {
        AtomicBoolean isFeatureEnabled = new AtomicBoolean(false);
        getFeatureFlagLoader().stream().findFirst().ifPresent(f -> isFeatureEnabled.set(f.isFeatureEnabled("asteroids")));

        if (isFeatureEnabled.get()) {
            typeId = world.generateTypeId();
            for (int i = 0; i < 25; i++) {
                Entity asteroid = createAsteroid(gameData);
                world.addEntity(asteroid);
            }
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(typeId)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Random rng = new Random();

        // The polygon-coordinates describe a shape that rather closely follows a circle with radius 1.
        int size = rng.nextInt(10) + 5;

        int x = rng.nextInt(gameData.getDisplayWidth());
        while (x > (gameData.getDisplayWidth() / 2) - 50 & x < (gameData.getDisplayWidth() / 2) + 50) {
            x = rng.nextInt(gameData.getDisplayWidth());
        }

        int y = rng.nextInt(gameData.getDisplayHeight());
        while (y > (gameData.getDisplayHeight() / 2) - 50 & y < (gameData.getDisplayHeight() / 2) + 50) {
            y = rng.nextInt(gameData.getDisplayHeight());
        }

        return AsteroidFactory.createAsteroid(typeId, x, y, rng.nextInt(360), size);
    }

    public static Collection<? extends IFeatureFlag> getFeatureFlagLoader() {
        return ServiceLoader.load(IFeatureFlag.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
