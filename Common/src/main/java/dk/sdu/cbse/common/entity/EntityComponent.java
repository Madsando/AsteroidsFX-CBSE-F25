package dk.sdu.cbse.common.entity;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface EntityComponent {
    void process(GameData gameData, World world, Entity entity);
}
