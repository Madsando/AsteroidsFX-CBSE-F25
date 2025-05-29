import dk.sdu.cbse.common.ui.*;
import dk.sdu.cbse.common.services.*;

module Core {
    requires Common;
    requires CommonUI;
    requires javafx.graphics;

    opens dk.sdu.cbse.main to javafx.graphics;

    uses IGamePluginService;
    uses ISystemService;
    uses IInputService;
    uses IGraphicsService;
    uses IBackgroundService;
}


