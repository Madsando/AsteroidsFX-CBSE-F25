import dk.sdu.cbse.input.spi.IInputSPI;
import dk.sdu.cbse.common.services.*;

module Core {
    requires Common;
    requires CommonInput;
    requires javafx.graphics;

    opens dk.sdu.cbse.main to javafx.graphics;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
    uses IInputSPI;
}


