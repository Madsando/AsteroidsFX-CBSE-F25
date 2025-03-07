package dk.sdu.cbse.collision.CollisionStrategy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.commoncollision.CollisionPair;
import dk.sdu.cbse.commoncollision.ECollisionType;

public interface ICollisionStrategy {
    CollisionPair<ECollisionType>[] getCollisionSignatures();
    void handleCollision(GameData gamedata, World world, CollisionPair<Entity> entityPair);
}
