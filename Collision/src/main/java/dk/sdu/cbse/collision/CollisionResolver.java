package dk.sdu.cbse.collision;

import dk.sdu.cbse.collision.CollisionStrategy.ICollisionStrategy;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.commoncollision.CollisionCP;
import dk.sdu.cbse.commoncollision.CollisionPair;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollisionResolverSPI;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class CollisionResolver implements ICollisionResolverSPI {
    private Map<CollisionPair<ECollisionType>, ICollisionStrategy> collisionMap;

    public CollisionResolver() {
        ConcurrentHashMap<CollisionPair<ECollisionType>, ICollisionStrategy> collisionMap = new ConcurrentHashMap<>();
        if (!getCollisionStrategies().isEmpty()) {
            for (ICollisionStrategy strategy : getCollisionStrategies()) {
                for (CollisionPair<ECollisionType> collisionPair : strategy.getCollisionSignatures()) {
                    collisionMap.put(collisionPair, strategy);
                }
            }
        }
    }

    @Override
    public void resolveCollision(GameData gamedata, World world, Entity entity, Entity otherEntity) {
        CollisionCP entityCollisionCP = entity.getComponent(CollisionCP.class);
        CollisionCP otherCollisionCP = otherEntity.getComponent(CollisionCP.class);

        HealthCP entityHealthCP = entity.getComponent(HealthCP.class);
        HealthCP otherHealthCP = otherEntity.getComponent(HealthCP.class);

        if (entityCollisionCP == null || otherCollisionCP == null || entityHealthCP == null || otherHealthCP == null) {
            return;
        }

        ECollisionType entityCT = entityCollisionCP.getCollisionType();
        ECollisionType otherCT = otherCollisionCP.getCollisionType();

        if (entityCT == otherCT) {
            switch (entityCT) {
                // Could add unique collisions later
                case ASTEROID, BULLET:
                    return;
                case ENTITY:
                    entityHealthCP.setHealth(0);
                    otherHealthCP.setHealth(0);
                    return;
            }
        }

        if (entityCT == ECollisionType.ASTEROID) {
            switch (otherCT) {
                case BULLET, ENTITY:
                    entityHealthCP.subtractHealth(1);
                    otherHealthCP.setHealth(0);
                    gamedata.addScore(1);
                    return;
            }
        }

        if (entityCT == ECollisionType.BULLET && otherCT == ECollisionType.ENTITY) {
            entityHealthCP.setHealth(0);
            otherHealthCP.subtractHealth(1);
            return;
        }
    }

    private Collection<? extends ICollisionStrategy> getCollisionStrategies() {
        return ServiceLoader.load(ICollisionStrategy.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
