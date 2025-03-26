import dk.sdu.cbse.common.ui.*;
import dk.sdu.cbse.common.services.*;

module Core {
    requires Common;
    requires CommonUI;
    requires javafx.graphics;

    requires spring.core;
    requires spring.context;
    requires spring.beans;

    opens dk.sdu.cbse.main to javafx.graphics,
            spring.core,
            spring.context,
            spring.beans;

    uses IGamePluginService;
    uses ISystemService;
    uses IInputService;
    uses IGraphicsService;
    uses IBackgroundService;
}


