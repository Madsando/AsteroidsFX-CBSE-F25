package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * @param gameData Used to access data related to the game
     * @param world Used to access the entities in the world
     */
    void process(GameData gameData, World world);
}
