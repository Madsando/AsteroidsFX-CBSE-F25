package dk.sdu.cbse.input;

import dk.sdu.cbse.common.input.spi.IInputSPI;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.EGameInputs;

public class InputHandler implements IInputSPI {
    @Override
    public EventHandler<InputEvent> getInputHandlerPress(GameData gameData) {
        return new EventHandler<>() {
            @Override
            public void handle(InputEvent inputEvent) {
                if (inputEvent instanceof KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case KeyCode.UP, KeyCode.W -> gameData.getInputs().setInput(EGameInputs.FORWARD, true);
                        case KeyCode.LEFT, KeyCode.A -> gameData.getInputs().setInput(EGameInputs.LEFT, true);
                        case KeyCode.RIGHT, KeyCode.D -> gameData.getInputs().setInput(EGameInputs.RIGHT, true);
                        case KeyCode.SPACE -> gameData.getInputs().setInput(EGameInputs.ACTION, true);
                    }
                }
            }
        };
    }

    @Override
    public EventHandler<InputEvent> getInputHandlerRelease(GameData gameData) {
        return new EventHandler<>() {
            @Override
            public void handle(InputEvent inputEvent) {
                if (inputEvent instanceof KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case KeyCode.UP, KeyCode.W -> gameData.getInputs().setInput(EGameInputs.FORWARD, false);
                        case KeyCode.LEFT, KeyCode.A -> gameData.getInputs().setInput(EGameInputs.LEFT, false);
                        case KeyCode.RIGHT, KeyCode.D -> gameData.getInputs().setInput(EGameInputs.RIGHT, false);
                        case KeyCode.SPACE -> gameData.getInputs().setInput(EGameInputs.ACTION, false);
                    }
                }
            }
        };
    }
}