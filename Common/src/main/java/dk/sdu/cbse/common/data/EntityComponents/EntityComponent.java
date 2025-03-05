package dk.sdu.cbse.common.data.EntityComponents;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface EntityComponent {
    public void process(GameData gameData, World world, Entity entity);
}
