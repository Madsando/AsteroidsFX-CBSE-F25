package dk.sdu.cbse.common.collision;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;

public interface ICollisionBehaviour {
    void process(GameData gameData, World world, Entity entity, EEntityType targetType);
}
