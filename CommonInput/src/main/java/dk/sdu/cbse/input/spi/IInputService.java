package dk.sdu.cbse.input.spi;

import dk.sdu.cbse.common.data.GameData;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public interface IInputService {
    public EventHandler<InputEvent> getInputHandlerPress(GameData gameData);

    public EventHandler<InputEvent> getInputHandlerRelease(GameData gameData);
}
