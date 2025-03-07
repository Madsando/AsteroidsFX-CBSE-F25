package dk.sdu.cbse.collision.CollisionStrategy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.commoncollision.CollisionPair;
import dk.sdu.cbse.commoncollision.ECollisionType;

public class DestroyStrategy implements ICollisionStrategy{
    @Override
    public CollisionPair<ECollisionType>[] getCollisionSignatures() {
        return new CollisionPair[]{new CollisionPair<>(ECollisionType.ENTITY, ECollisionType.ENTITY)};
    }

    @Override
    public void handleCollision(GameData gamedata, World world, CollisionPair<Entity> entityPair) {
        HealthCP e = entityPair.getK().getComponent(HealthCP.class);
        e.setHealth(0);

        HealthCP e2 = entityPair.getV().getComponent(HealthCP.class);
        e2.setHealth(0);
    }
}
