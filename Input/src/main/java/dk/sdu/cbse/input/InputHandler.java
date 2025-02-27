package dk.sdu.cbse.input;

import dk.sdu.cbse.input.spi.IInputSPI;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;

public class InputHandler implements IInputSPI {
    @Override
    public EventHandler<InputEvent> getInputHandlerPress(GameData gameData) {
        return new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent inputEvent) {
                if (inputEvent instanceof KeyEvent keyEvent) {
                    System.out.println("KeyEvent: " + keyEvent.getCode());
                    switch (keyEvent.getCode()) {
                        case UP -> gameData.getKeys().setKey(GameKeys.UP, true);
                        case LEFT -> gameData.getKeys().setKey(GameKeys.LEFT, true);
                        case RIGHT -> gameData.getKeys().setKey(GameKeys.RIGHT, true);
                        case SPACE -> gameData.getKeys().setKey(GameKeys.SPACE, true);
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
                        case UP -> gameData.getKeys().setKey(GameKeys.UP, false);
                        case LEFT -> gameData.getKeys().setKey(GameKeys.LEFT, false);
                        case RIGHT -> gameData.getKeys().setKey(GameKeys.RIGHT, false);
                        case SPACE -> gameData.getKeys().setKey(GameKeys.SPACE, false);
                    }
                }
            }
        };
    }
}