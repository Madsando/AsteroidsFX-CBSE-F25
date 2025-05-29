package dk.sdu.cbse.common.ui;

import dk.sdu.cbse.common.input.GameInputs;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public interface IInputService {
    EventHandler<InputEvent> getInputHandlerPress(GameInputs gameInputs);

    EventHandler<InputEvent> getInputHandlerRelease(GameInputs gameInputs);
}
