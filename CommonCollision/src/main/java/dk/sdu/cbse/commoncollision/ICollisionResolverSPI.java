package dk.sdu.cbse.commoncollision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

public interface ICollisionResolverSPI {
    public void handleCollision(World world, Entity entity, Entity otherEntity);
}
