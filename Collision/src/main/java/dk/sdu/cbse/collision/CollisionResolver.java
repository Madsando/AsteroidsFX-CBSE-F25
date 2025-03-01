package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollidableEntity;
import dk.sdu.cbse.commoncollision.ICollisionResolverSPI;

public class CollisionResolver implements ICollisionResolverSPI {
    @Override
    public void handleCollision(World world, Entity entity, Entity otherEntity) {
        if (entity instanceof ICollidableEntity e && otherEntity instanceof ICollidableEntity e2) {
            ECollisionType t = e.getCollisionType();
            ECollisionType t2 = e2.getCollisionType();

            if (t == t2 ) {
                if (t == ECollisionType.ENTITY) {
                    world.removeEntity(entity);
                    world.removeEntity(otherEntity);
                    return;
                }
                // Could be a unique collision between bullets, but ignoring it for now
                /*if (t == ECollisionType.BULLET) {
                    return;
                }*/
            }

            if (t == ECollisionType.ENTITY && t2 == ECollisionType.BULLET) {
                //TODO: entity loses life
            }

        }
    }
}
