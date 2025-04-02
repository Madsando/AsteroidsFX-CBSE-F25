package dk.sdu.cbse.common.collision;

import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.utility.UnorderedPair;

public interface ICollisionStrategy {
    UnorderedPair<EEntityType>[] getCollisionSignatures();
    void handleCollision(GameData gamedata, World world, UnorderedPair<Entity> entityPair);
}
