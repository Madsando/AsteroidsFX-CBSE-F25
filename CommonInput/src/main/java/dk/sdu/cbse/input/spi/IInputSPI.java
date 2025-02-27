package dk.sdu.cbse.input.spi;

import dk.sdu.cbse.common.data.GameData;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public interface IInputSPI {
    public EventHandler<InputEvent> getInputHandlerPress(GameData gameData);

    public EventHandler<InputEvent> getInputHandlerRelease(GameData gameData);
}
