package dk.sdu.cbse.collisionstrategies;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.utility.UnorderedPair;
import dk.sdu.cbse.common.collision.ECollisionType;
import dk.sdu.cbse.common.collision.ICollisionStrategy;

public class EntityStrategy implements ICollisionStrategy {
    @Override
    public UnorderedPair<ECollisionType>[] getCollisionSignatures() {
        return new UnorderedPair[]{
                new UnorderedPair<>(ECollisionType.ENTITY, ECollisionType.BULLET),
        };
    }

    @Override
    public void handleCollision(GameData gamedata, World world, UnorderedPair<Entity> entityPair) {
        CollisionCP entityCCP = entityPair.getK().getComponent(CollisionCP.class);
        if (entityCCP.getCollisionType().equals(ECollisionType.ENTITY)) {
            HealthCP e = entityPair.getK().getComponent(HealthCP.class);
            e.subtractHealth(1);

            HealthCP e2 = entityPair.getV().getComponent(HealthCP.class);
            e2.setHealth(0);
        } else {
            HealthCP e = entityPair.getV().getComponent(HealthCP.class);
            e.subtractHealth(1);

            HealthCP e2 = entityPair.getK().getComponent(HealthCP.class);
            e2.setHealth(0);
        }

        gamedata.addScore(1);
    }
}
