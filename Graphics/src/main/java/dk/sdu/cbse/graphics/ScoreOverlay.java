package dk.sdu.cbse.graphics;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.score.ScoreService;
import dk.sdu.cbse.common.ui.IGraphicsService;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ScoreOverlay implements IGraphicsService {
    private Text scoreText;

    @Override
    public Node createComponent(GameData gameData, World world) {
        scoreText = new Text(10, 20, "Destroyed asteroids: 0");
        scoreText.setFill(Color.WHITE);
        return scoreText;
    }

    @Override
    public void updateComponent(GameData gameData, World world) {
        int score = -2;
        if (getScoreServices().stream().findFirst().isPresent()) {
            score = getScoreServices().stream().findFirst().get().getScore();
        }
        scoreText.setText(String.format("Destroyed asteroids: %d", score));
    }

    public static List<ScoreService> getScoreServices() {
        return ServiceLoader.load(ScoreService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
