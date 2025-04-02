package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.graphics.IBackgroundComponent;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;
import dk.sdu.cbse.common.input.spi.IInputSPI;
import dk.sdu.cbse.common.services.*;

import java.util.List;

import dk.sdu.cbse.common.data.World;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public class Game {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Pane gameWindow = new Pane();

    @Autowired
    private List<IGraphicsComponent> iGraphicsComponents;

    @Autowired
    private List<IEntityProcessingService> iEntityProcessingServices;

    @Autowired
    private List<IPostEntityProcessingService> iPostEntityProcessingServices;

    @Autowired
    private List<IGamePluginService> iGamePluginServices;

    @Autowired
    private List<IInputSPI> iInputSPIs;

    @Autowired
    private List<IBackgroundComponent> iBackgroundComponents;

    public void start(Stage window) throws Exception {
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        Scene scene = new Scene(gameWindow);

        gameWindow.heightProperty().addListener((observable, oldValue, newValue) -> {
            gameData.setDisplayHeight(newValue.intValue());
        });
        gameWindow.widthProperty().addListener((observable, oldValue, newValue) -> {
            gameData.setDisplayWidth(newValue.intValue());
        });

        gameWindow.setBackground(iBackgroundComponents.getFirst().getBackground());
        scene.setOnKeyPressed(iInputSPIs.getFirst().getInputHandlerPress(gameData));
        scene.setOnKeyReleased(iInputSPIs.getFirst().getInputHandlerRelease(gameData));

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : iGamePluginServices) {
            iGamePlugin.start(gameData, world);
        }

        for (IGraphicsComponent graphicsComponent : iGraphicsComponents) {
            gameWindow.getChildren().add(graphicsComponent.createComponent(gameData, world));
        }

        render();
        window.setScene(scene);
        window.setTitle("Asteroids");
        window.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameData.getInputs().update();
                update();
                draw();
            }
        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : iEntityProcessingServices) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : iPostEntityProcessingServices) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (IGraphicsComponent graphicsComponent : iGraphicsComponents) {
            graphicsComponent.updateComponent(gameData, world);
        }
    }
}
