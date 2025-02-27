module Input {
    requires Common;
    requires javafx.graphics;
    requires CommonInput;
    provides dk.sdu.cbse.input.spi.IInputService with dk.sdu.cbse.input.InputHandler;
}


