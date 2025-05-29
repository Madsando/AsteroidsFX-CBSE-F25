import dk.sdu.cbse.common.ui.IInputService;

module Input {
    requires Common;
    requires CommonUI;
    requires javafx.graphics;
    provides IInputService with dk.sdu.cbse.input.InputHandler;
}


