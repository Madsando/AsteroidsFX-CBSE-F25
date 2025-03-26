import dk.sdu.cbse.common.graphics.IBackgroundComponent;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;
import dk.sdu.cbse.common.input.spi.IInputSPI;
import dk.sdu.cbse.common.services.*;

module Core {
    requires Common;
    requires CommonInput;
    requires CommonGraphics;
    requires javafx.graphics;

    requires spring.core;
    requires spring.context;
    requires spring.beans;

    opens dk.sdu.cbse.main to javafx.graphics,
            spring.core,
            spring.context,
            spring.beans;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
    uses IInputSPI;
    uses IGraphicsComponent;
    uses IBackgroundComponent;
}


