package dk.sdu.cbse.collision;

import dk.sdu.cbse.commoncollision.ICollisionStrategy;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commoncollision.CollisionCP;
import dk.sdu.cbse.commoncollision.CollisionPair;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollisionResolverSPI;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class CollisionResolver implements ICollisionResolverSPI {
    private final Map<CollisionPair<ECollisionType>, ICollisionStrategy> collisionMap;

    public CollisionResolver() {
        collisionMap = new ConcurrentHashMap<>();
        if (!getCollisionStrategies().isEmpty()) {
            for (ICollisionStrategy strategy : getCollisionStrategies()) {
                for (CollisionPair<ECollisionType> collisionPair : strategy.getCollisionSignatures()) {
                    collisionMap.put(collisionPair, strategy);
                }
            }
        }
    }

    @Override
    public void resolveCollision(GameData gamedata, World world, CollisionPair<Entity> entityPair) {
        Entity e = entityPair.getK();
        Entity e2 = entityPair.getV();

        CollisionCP entityCollisionCP = e.getComponent(CollisionCP.class);
        CollisionCP otherCollisionCP = e2.getComponent(CollisionCP.class);

        if (entityCollisionCP == null | otherCollisionCP == null) {
            return;
        }

        CollisionPair<ECollisionType> collisionPair = new CollisionPair<>(entityCollisionCP.getCollisionType(), otherCollisionCP.getCollisionType());

        if (collisionMap.containsKey(collisionPair)) {
            collisionMap.get(collisionPair).handleCollision(gamedata, world, entityPair);
        }
    }

    private Collection<? extends ICollisionStrategy> getCollisionStrategies() {
        return ServiceLoader.load(ICollisionStrategy.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
