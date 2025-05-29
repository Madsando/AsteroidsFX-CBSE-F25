package dk.sdu.cbse.common.ui;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import javafx.scene.Node;

public interface IGraphicsService {
    Node createComponent(GameData gameData, World world);
    void updateComponent(GameData gameData, World world);
}
