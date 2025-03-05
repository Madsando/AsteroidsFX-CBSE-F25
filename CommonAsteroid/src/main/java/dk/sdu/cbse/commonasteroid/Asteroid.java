package dk.sdu.cbse.commonasteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollidableEntity;

public class Asteroid extends Entity implements ICollidableEntity {
    @Override
    public ECollisionType getCollisionType() {
        return ECollisionType.ASTEROID;
    }
}
