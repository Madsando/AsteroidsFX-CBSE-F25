package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface ISystemService {
    int getPriority();
    void update(GameData gameData, World world);
}
