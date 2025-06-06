package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.ICustomEntityBehaviour;
import dk.sdu.cbse.common.entitycomponents.*;

public class AsteroidSplitter implements ICustomEntityBehaviour {
    @Override
    public void process(GameData gameData, World world, Entity entity) {
        gameData.addScore(1);

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
}
