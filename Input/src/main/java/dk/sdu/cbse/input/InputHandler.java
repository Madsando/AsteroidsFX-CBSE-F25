package dk.sdu.cbse.input;

import dk.sdu.cbse.input.spi.IInputSPI;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.EGameInputs;

public class InputHandler implements IInputSPI {
    @Override
    public EventHandler<InputEvent> getInputHandlerPress(GameData gameData) {
        return new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent inputEvent) {
                if (inputEvent instanceof KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case UP, KeyCode.W -> gameData.getInputs().setInput(EGameInputs.FORWARD, true);
                        case LEFT, KeyCode.A -> gameData.getInputs().setInput(EGameInputs.LEFT, true);
                        case RIGHT, KeyCode.D -> gameData.getInputs().setInput(EGameInputs.RIGHT, true);
                        case SPACE -> gameData.getInputs().setInput(EGameInputs.ACTION, true);
                    }
                }
            }
        };
    }

    @Override
    public EventHandler<InputEvent> getInputHandlerRelease(GameData gameData) {
        return new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent inputEvent) {
                if (inputEvent instanceof KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case UP, KeyCode.W -> gameData.getInputs().setInput(EGameInputs.FORWARD, false);
                        case LEFT, KeyCode.A -> gameData.getInputs().setInput(EGameInputs.LEFT, false);
                        case RIGHT, KeyCode.D -> gameData.getInputs().setInput(EGameInputs.RIGHT, false);
                        case SPACE -> gameData.getInputs().setInput(EGameInputs.ACTION, false);
                    }
                }
            }
        };
    }
}