package dk.sdu.cbse.commonbullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollidableEntity;

public class Bullet extends Entity implements ICollidableEntity {
    @Override
    public ECollisionType getCollisionType() {
        return ECollisionType.BULLET;
    }
}
