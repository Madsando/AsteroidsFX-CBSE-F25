package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface CustomEntityBehaviour {
    public void process(GameData gameData, World world, Entity entity);
}
