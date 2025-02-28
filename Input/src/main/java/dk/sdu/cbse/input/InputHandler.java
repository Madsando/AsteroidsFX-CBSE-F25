package dk.sdu.cbse.input;

import dk.sdu.cbse.input.spi.IInputSPI;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.EGameKeys;

public class InputHandler implements IInputSPI {
    @Override
    public EventHandler<InputEvent> getInputHandlerPress(GameData gameData) {
        return new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent inputEvent) {
                if (inputEvent instanceof KeyEvent keyEvent) {
                    System.out.println("KeyEvent: " + keyEvent.getCode());
                    switch (keyEvent.getCode()) {
                        case UP, KeyCode.W -> gameData.getKeys().setKey(EGameKeys.UP, true);
                        case LEFT, KeyCode.A -> gameData.getKeys().setKey(EGameKeys.LEFT, true);
                        case RIGHT, KeyCode.D -> gameData.getKeys().setKey(EGameKeys.RIGHT, true);
                        case SPACE -> gameData.getKeys().setKey(EGameKeys.ACTION, true);
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
                        case UP, KeyCode.W -> gameData.getKeys().setKey(EGameKeys.UP, false);
                        case LEFT, KeyCode.A -> gameData.getKeys().setKey(EGameKeys.LEFT, false);
                        case RIGHT, KeyCode.D -> gameData.getKeys().setKey(EGameKeys.RIGHT, false);
                        case SPACE -> gameData.getKeys().setKey(EGameKeys.ACTION, false);
                    }
                }
            }
        };
    }
}