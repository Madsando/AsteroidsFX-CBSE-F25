package dk.sdu.cbse.star;

import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.PositionCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IFeatureFlag;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.stream.Collectors.toList;

public class StarPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        AtomicBoolean isFeatureEnabled = new AtomicBoolean(false);
        getFeatureFlagLoader().stream().findFirst().ifPresent(f -> {
            isFeatureEnabled.set(f.isFeatureEnabled("stars"));});

        if (isFeatureEnabled.get()) {
            for (int i = 0; i < 150; i++) {
                Entity star = createStar(gameData);
                world.addEntity(star);
            }
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity star : world.getEntities(EEntityType.OTHER)) {
            world.removeEntity(star);
        }
    }

    private Entity createStar(GameData gameData) {
        Entity star = new Entity(EEntityType.OTHER);
        Random rng = new Random();

        // The polygon-coordinates describe a shape that rather closely follows a circle with radius 1.
        double[] polygonCoordinates = {1, 1, 1, -1, -1, -1, -1, 1};
        double scalingFactor = rng.nextDouble(0,1) + 1;
        for (int i = 0; i < polygonCoordinates.length; i++) {
            polygonCoordinates[i] *= scalingFactor;
        }

        int color = rng.nextInt(120, 255);
        star.addComponent(new ShapeCP(
                polygonCoordinates,
                scalingFactor,
                new int[]{color, color, color}
        ));

        double angle = rng.nextDouble(0, 2 * Math.PI);
        double radius = rng.nextDouble(365, 500);

        int x = (int) (Math.cos(angle) * radius) + gameData.getDisplayWidth() / 2;
        int y = (int) (Math.sin(angle) * radius) + gameData.getDisplayHeight() / 2;

        star.addComponent(new PositionCP(
                x,
                y,
                rng.nextInt(0, 180)
        ));

        return star;
    }

    public static Collection<? extends IFeatureFlag> getFeatureFlagLoader() {
        return ServiceLoader.load(IFeatureFlag.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
