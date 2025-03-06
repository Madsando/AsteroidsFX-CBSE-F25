package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.commoncollision.CollisionCP;
import dk.sdu.cbse.commoncollision.ECollisionType;
import dk.sdu.cbse.commoncollision.ICollisionResolverSPI;


public class CollisionResolver implements ICollisionResolverSPI {
    @Override
    public void handleCollision(GameData gamedata, World world, Entity entity, Entity otherEntity) {
        CollisionCP entityCollisionCP = entity.getComponent(CollisionCP.class);
        CollisionCP otherCollisionCP = otherEntity.getComponent(CollisionCP.class);

        HealthCP entityHealthCP = entity.getComponent(HealthCP.class);
        HealthCP otherHealthCP = otherEntity.getComponent(HealthCP.class);

        if (entityCollisionCP == null || otherCollisionCP == null || entityHealthCP == null || otherHealthCP == null) {
            return;
        }

        ECollisionType entityCT = entityCollisionCP.getCollisionType();
        ECollisionType otherCT = otherCollisionCP.getCollisionType();

        if (entityCT == otherCT) {
            switch (entityCT) {
                // Could add unique collisions later
                case ASTEROID, BULLET:
                    return;
                case ENTITY:
                    entityHealthCP.setHealth(0);
                    otherHealthCP.setHealth(0);
                    return;
            }
        }

        if (entityCT == ECollisionType.ASTEROID) {
            switch (otherCT) {
                case BULLET, ENTITY:
                    entityHealthCP.subtractHealth(1);
                    otherHealthCP.setHealth(0);
                    gamedata.addScore(1);
                    return;
            }
        }

        if (entityCT == ECollisionType.BULLET && otherCT == ECollisionType.ENTITY) {
            entityHealthCP.setHealth(0);
            otherHealthCP.subtractHealth(1);
            return;
        }
    }
}
