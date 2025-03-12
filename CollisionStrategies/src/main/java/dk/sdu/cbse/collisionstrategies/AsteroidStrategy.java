package dk.sdu.cbse.collisionstrategies;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.utility.UnorderedPair;
import dk.sdu.cbse.common.collision.ECollisionType;
import dk.sdu.cbse.common.collision.ICollisionStrategy;

public class AsteroidStrategy implements ICollisionStrategy {
    @Override
    public UnorderedPair<ECollisionType>[] getCollisionSignatures() {
        return new UnorderedPair[]{
                new UnorderedPair<>(ECollisionType.ASTEROID, ECollisionType.ENTITY),
                new UnorderedPair<>(ECollisionType.ASTEROID, ECollisionType.BULLET)
        };
    }

    @Override
    public void handleCollision(GameData gamedata, World world, UnorderedPair<Entity> entityPair) {
        HealthCP e = entityPair.getK().getComponent(HealthCP.class);
        e.setHealth(0);

        HealthCP e2 = entityPair.getV().getComponent(HealthCP.class);
        e2.setHealth(0);

        gamedata.addScore(1);
    }
}
