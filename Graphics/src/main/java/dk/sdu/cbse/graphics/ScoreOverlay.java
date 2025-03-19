package dk.sdu.cbse.graphics;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ScoreOverlay implements IGraphicsComponent {
    private Text scoreText;

    @Override
    public Node createComponent(GameData gameData, World world) {
        scoreText = new Text(10, 20, "Destroyed asteroids: 0");
        scoreText.setFill(Color.WHITE);
        return scoreText;
    }

    @Override
    public void updateComponent(GameData gameData, World world) {
        scoreText.setText(String.format("Destroyed asteroids: %d", gameData.getScore()));
    }
}
