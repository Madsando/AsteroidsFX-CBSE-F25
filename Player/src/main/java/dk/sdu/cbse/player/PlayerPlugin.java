package dk.sdu.cbse.player;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.entity.EEntityType;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.stream.Collectors.toList;

public class PlayerPlugin implements IGamePluginService {
    private static int typeId = 0;

    @Override
    public void start(GameData gameData, World world) {
        AtomicBoolean isFeatureEnabled = new AtomicBoolean(false);
        getFeatureFlagLoader().stream().findFirst().ifPresent(f -> isFeatureEnabled.set(f.isFeatureEnabled("player")));

        if (isFeatureEnabled.get()) {
            typeId = world.generateTypeId();
            Entity player = createPlayer(gameData);
            world.addEntity(player);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity player : world.getEntities(typeId)) {
            world.removeEntity(player);
        }
    }

    private Entity createPlayer(GameData gameData) {
        Entity player = new Entity(typeId);

        double scalingFactor = 5;
        double size = 3 * scalingFactor;
        //double[] polygonCoordinates = {9, 0, -0.25,-2.7, 2, -6, -4.6, -4.4, -9,- 5.5, -9, 5.5, -4.6, 4.4, 2, 6, -0.25, 2.7};
        double[] polygonCoordinates = {3.4, -0.05, 2.65, -0.2, 0, -0.5, 0, -0.95, -0.3, -0.95, -0.3, -1.25, -0.55, -2.55, 0.15, -2.55, 0.2, -2.6, 1.25, -2.6, 1.7, -2.65, 1.25, -2.7, 0.15, -2.7, 0.1, -2.75, -1.4, -2.75, -1.55, -2.7, -1.55, -2.6, -1.35, -2.55, -1.8, -0.95, -1.55, -0.95, -1.6, -0.85, -2, -0.85, -2.1, -0.9, -2.5, -0.85, -2.5, -0.55, -1.9, -0.55, -1.9, -0.37, -2.15, -0.15,
                                    -2.15, 0.15, -1.9, 0.37, -1.9, 0.55, -2.5, 0.55, -2.5, 0.85, -2.1, 0.9, -2, 0.85, -1.6, 0.85, -1.55, 0.95, -1.8, 0.95, -1.35, 2.55, -1.55, 2.6, -1.55, 2.7, -1.4, 2.75, 0.1, 2.75, 0.15, 2.7, 1.25, 2.7, 1.7, 2.65, 1.25, 2.6, 0.2, 2.6, 0.15, 2.55, -0.55, 2.55, -0.3, 2.55, -0.3, 0.95, 0, 0.95, 0, 0.5, 2.65, 0.2, 3.4, 0.05};

        for (int i = 0; i < polygonCoordinates.length; i++) {
            polygonCoordinates[i] *= scalingFactor;
        }

        player.addComponent(new ShapeCP(
                polygonCoordinates,
                new int[]{0, 0, 254}
        ));

        player.addComponent(new TransformCP(
                (double) gameData.getDisplayWidth() / 2,
                (double) gameData.getDisplayHeight() / 2,
                270,
                size
        ));

        player.addComponent(new HealthCP(
                3,
                null
        ));

        player.addComponent(new BulletCP(
                1,
                75,
                false
        ));

        player.addComponent(new MovementCP(
                1,
                3,
                false,
                false,
                false
        ));

        player.addComponent(new WraparoundCP());

        player.addComponent(new InputMovementControlCP());

        player.addComponent(new InputBulletControlCP());

        player.addComponent(new DamageCP(Integer.MAX_VALUE));

        player.addComponent(new CollisionCP());

        return player;
    }

    public static Collection<? extends IFeatureFlag> getFeatureFlagLoader() {
        return ServiceLoader.load(IFeatureFlag.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
