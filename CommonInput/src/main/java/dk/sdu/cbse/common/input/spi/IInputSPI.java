package dk.sdu.cbse.common.input.spi;

import dk.sdu.cbse.common.data.GameData;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;

public interface IInputSPI {
    EventHandler<InputEvent> getInputHandlerPress(GameData gameData);

    EventHandler<InputEvent> getInputHandlerRelease(GameData gameData);
}
