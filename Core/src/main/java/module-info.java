module Core {
    requires Common;
    requires javafx.graphics;
    requires CommonInput;
    opens dk.sdu.cbse.main to javafx.graphics;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.cbse.input.spi.IInputService;
}


