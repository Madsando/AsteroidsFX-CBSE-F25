package dk.sdu.cbse.collisionstrategies;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.commoncollision.CollisionCP;
import dk.sdu.cbse.commoncollision.Pair;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollisionStrategy;

public class EntityStrategy implements ICollisionStrategy {
    @Override
    public Pair<ECollisionType>[] getCollisionSignatures() {
        return new Pair[]{
                new Pair<>(ECollisionType.ENTITY, ECollisionType.BULLET),
        };
    }

    @Override
    public void handleCollision(GameData gamedata, World world, Pair<Entity> entityPair) {
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
