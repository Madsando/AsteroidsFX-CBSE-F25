package dk.sdu.cbse.commoncollision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface ICollisionStrategy {
    Pair<ECollisionType>[] getCollisionSignatures();
    void handleCollision(GameData gamedata, World world, Pair<Entity> entityPair);
}
