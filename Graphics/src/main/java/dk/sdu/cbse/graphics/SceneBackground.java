package dk.sdu.cbse.graphics;

import dk.sdu.cbse.common.ui.IBackgroundService;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Collections;

public class SceneBackground implements IBackgroundService {
    @Override
    public Background getBackground() {
        Image image = new Image("deathstar_nostars.png", true);
        return new Background(
                Collections.singletonList(new BackgroundFill(Color.BLACK, null, null)),
                Collections.singletonList(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)));
    }
}
