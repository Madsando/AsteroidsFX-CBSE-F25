import dk.sdu.cbse.input.spi.IInputSPI;

module Input {
    requires Common;
    requires CommonInput;
    requires javafx.graphics;
    provides IInputSPI with dk.sdu.cbse.input.InputHandler;
}


