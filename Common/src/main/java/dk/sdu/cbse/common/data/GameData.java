package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.input.GameInputs;

public class GameData {
    private static GameData instance = null;

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameInputs inputs = new GameInputs();

    private GameData() {}

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public GameInputs getInputs() {
        return inputs;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }
}
