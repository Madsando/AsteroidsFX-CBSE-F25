package dk.sdu.cbse.common.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.utility.UnorderedPair;

public interface ICollisionResolverSPI {
    public void resolveCollision(GameData gamedata, World world, UnorderedPair<Entity> entityPair);
}
