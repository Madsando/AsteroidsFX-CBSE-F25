package dk.sdu.cbse.common.input;

import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public interface IInputSPI {
    EventHandler<InputEvent> getInputHandlerPress(GameInputs gameInputs);

    EventHandler<InputEvent> getInputHandlerRelease(GameInputs gameInputs);
}
