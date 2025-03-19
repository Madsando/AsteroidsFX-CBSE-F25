package dk.sdu.cbse.common.graphics;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import javafx.scene.Node;

public interface IGraphicsComponent {
    Node createComponent(GameData gameData, World world);
    void updateComponent(GameData gameData, World world);
}
