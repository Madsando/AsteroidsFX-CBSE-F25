package dk.sdu.cbse.common.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface ICollisionStrategy {
    Pair<ECollisionType>[] getCollisionSignatures();
    void handleCollision(GameData gamedata, World world, Pair<Entity> entityPair);
}
