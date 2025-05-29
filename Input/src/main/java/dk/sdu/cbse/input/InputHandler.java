package dk.sdu.cbse.input;

import dk.sdu.cbse.common.input.GameInputs;
import dk.sdu.cbse.common.ui.IInputService;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import dk.sdu.cbse.common.input.EGameInputs;

public class InputHandler implements IInputService {
    @Override
    public EventHandler<InputEvent> getInputHandlerPress(GameInputs gameInputs) {
        return new EventHandler<>() {
            @Override
            public void handle(InputEvent inputEvent) {
                if (inputEvent instanceof KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case KeyCode.UP, KeyCode.W -> gameInputs.setInput(EGameInputs.FORWARD, true);
                        case KeyCode.LEFT, KeyCode.A -> gameInputs.setInput(EGameInputs.LEFT, true);
                        case KeyCode.RIGHT, KeyCode.D -> gameInputs.setInput(EGameInputs.RIGHT, true);
                        case KeyCode.SPACE -> gameInputs.setInput(EGameInputs.ACTION, true);
                    }
                }
            }
        };
    }

    @Override
    public EventHandler<InputEvent> getInputHandlerRelease(GameInputs gameInputs) {
        return new EventHandler<>() {
            @Override
            public void handle(InputEvent inputEvent) {
                if (inputEvent instanceof KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case KeyCode.UP, KeyCode.W -> gameInputs.setInput(EGameInputs.FORWARD, false);
                        case KeyCode.LEFT, KeyCode.A -> gameInputs.setInput(EGameInputs.LEFT, false);
                        case KeyCode.RIGHT, KeyCode.D -> gameInputs.setInput(EGameInputs.RIGHT, false);
                        case KeyCode.SPACE -> gameInputs.setInput(EGameInputs.ACTION, false);
                    }
                }
            }
        };
    }
}