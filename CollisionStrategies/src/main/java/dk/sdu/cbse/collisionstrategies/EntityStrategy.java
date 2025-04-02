package dk.sdu.cbse.collisionstrategies;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.utility.UnorderedPair;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.collision.ICollisionStrategy;

public class EntityStrategy implements ICollisionStrategy {
    @Override
    public UnorderedPair<EEntityType>[] getCollisionSignatures() {
        return new UnorderedPair[]{
                new UnorderedPair<>(EEntityType.ENEMY, EEntityType.BULLET),
                new UnorderedPair<>(EEntityType.PLAYER, EEntityType.BULLET),
        };
    }

    @Override
    public void handleCollision(GameData gamedata, World world, UnorderedPair<Entity> entityPair) {
        if (entityPair.getK().getEntityType().equals(EEntityType.BULLET)) {
            HealthCP e = entityPair.getK().getComponent(HealthCP.class);
            e.setHealth(0);

            HealthCP e2 = entityPair.getV().getComponent(HealthCP.class);
            e2.subtractHealth(1);
        } else {
            HealthCP e = entityPair.getK().getComponent(HealthCP.class);
            e.subtractHealth(1);

            HealthCP e2 = entityPair.getV().getComponent(HealthCP.class);
            e2.setHealth(0);
        }

        gamedata.addScore(1);
    }
}
