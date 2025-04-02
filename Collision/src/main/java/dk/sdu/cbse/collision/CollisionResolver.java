package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.collision.ICollisionStrategy;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.utility.UnorderedPair;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.collision.ICollisionResolverSPI;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class CollisionResolver implements ICollisionResolverSPI {
    private final Map<UnorderedPair<EEntityType>, ICollisionStrategy> collisionMap;

    public CollisionResolver() {
        collisionMap = new ConcurrentHashMap<>();
        if (!getCollisionStrategies().isEmpty()) {
            for (ICollisionStrategy strategy : getCollisionStrategies()) {
                for (UnorderedPair<EEntityType> pair : strategy.getCollisionSignatures()) {
                    collisionMap.put(pair, strategy);
                }
            }
        }
    }

    @Override
    public void resolveCollision(GameData gamedata, World world, UnorderedPair<Entity> entityPair) {
        CollisionCP entityCollisionCP = entityPair.getK().getComponent(CollisionCP.class);
        CollisionCP otherCollisionCP = entityPair.getV().getComponent(CollisionCP.class);

        if (entityCollisionCP == null | otherCollisionCP == null) {
            return;
        }

        UnorderedPair<EEntityType> collisionSignature = new UnorderedPair<>(entityCollisionCP.getCollisionType(), otherCollisionCP.getCollisionType());

        if (collisionMap.containsKey(collisionSignature)) {
            collisionMap.get(collisionSignature).handleCollision(gamedata, world, entityPair);
        }
    }

    private Collection<? extends ICollisionStrategy> getCollisionStrategies() {
        return ServiceLoader.load(ICollisionStrategy.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }
}
