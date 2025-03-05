package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commonasteroid.SplitAsteroid;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollidableEntity;
import dk.sdu.cbse.commoncollision.ICollisionResolverSPI;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionResolver implements ICollisionResolverSPI {
    @Override
    public void handleCollision(GameData gamedata, World world, Entity entity, Entity otherEntity) {
        if (entity instanceof ICollidableEntity e && otherEntity instanceof ICollidableEntity e2) {
            ECollisionType t = e.getCollisionType();
            ECollisionType t2 = e2.getCollisionType();

            if (t == t2) {
                switch (t) {
                    // Could add unique collisions later
                    case ASTEROID, BULLET:
                        return;
                    case ENTITY:
                        world.removeEntity(entity);
                        world.removeEntity(otherEntity);
                        return;
                }
            }

            if (t == ECollisionType.ASTEROID) {
                // TODO: Should remove life. But not for now
                switch (t2) {
                    case BULLET, ENTITY:
                        getAsteroidSplitter().createSplitAsteroids(world, entity);
                        world.removeEntity(otherEntity);
                        gamedata.addScore(1);
                        return;
                }
            }

            if (t == ECollisionType.BULLET && t2 == ECollisionType.ENTITY) {
                world.removeEntity(entity);
                world.removeEntity(otherEntity);
                return;
            }

        }
    }

    private SplitAsteroid getAsteroidSplitter() {
        Collection<? extends SplitAsteroid> AsteroidSplitCollection = ServiceLoader.load(SplitAsteroid.class).stream().map(ServiceLoader.Provider::get).collect(toList());

        if (AsteroidSplitCollection.stream().findFirst().isPresent()) {
            return AsteroidSplitCollection.stream().findFirst().get();
        }
        else {
            return null;
        }
    }
}
