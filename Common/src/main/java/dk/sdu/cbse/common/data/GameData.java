package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.input.GameInputs;

public class GameData {
    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private int score = 0;
    private final GameInputs inputs = new GameInputs();

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

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
