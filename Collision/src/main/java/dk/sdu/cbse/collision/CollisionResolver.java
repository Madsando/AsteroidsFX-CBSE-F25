package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollidableEntity;
import dk.sdu.cbse.commoncollision.ICollisionResolverSPI;


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
                        entity.setHealth(0);
                        entity.setHealth(0);
                        return;
                }
            }

            if (t == ECollisionType.ASTEROID) {
                switch (t2) {
                    case BULLET, ENTITY:
                        entity.decrementHealth();
                        otherEntity.setHealth(0);
                        gamedata.addScore(1);
                        return;
                }
            }

            if (t == ECollisionType.BULLET && t2 == ECollisionType.ENTITY) {
                entity.setHealth(0);
                otherEntity.decrementHealth();
                return;
            }

        }
    }
}
