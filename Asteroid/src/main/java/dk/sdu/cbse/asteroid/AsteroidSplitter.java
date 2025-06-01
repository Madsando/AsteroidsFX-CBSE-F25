package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.score.ScoreService;
import dk.sdu.cbse.common.services.ICustomEntityBehaviour;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class AsteroidSplitter implements ICustomEntityBehaviour {
    @Override
    public void process(GameData gameData, World world, Entity entity) {
        getScoreServices().stream().findFirst().ifPresent(scoreService -> {scoreService.updateScore(1);});

        TransformCP transformCP = entity.getComponent(TransformCP.class);
        double x = transformCP.getX();
        double y = transformCP.getY();
        double rotation = transformCP.getRotation();
        double asteroidSize = transformCP.getRadius() / 2;

        if (asteroidSize < 5) { // Stop if the split asteroid is too small
            return;
        }

        for (int i = -1; i <= 1; i += 2) {
            double asteroidX = x + i * asteroidSize;
            double asteroidY = y + i * asteroidSize;
            double asteroidRotation = rotation + i * 90;

            Entity asteroid = AsteroidFactory.createAsteroid(entity.getTypeID(), asteroidX, asteroidY, asteroidRotation, asteroidSize);

            world.addEntity(asteroid);
        }
    }

    public static List<ScoreService> getScoreServices() {
        return ServiceLoader.load(ScoreService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
