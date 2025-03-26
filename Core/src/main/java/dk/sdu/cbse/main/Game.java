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

public class Game {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Pane gameWindow = new Pane();
    private List<IGraphicsComponent> iGraphicsComponents;
    private List<IEntityProcessingService> iEntityProcessingServices;
    private List<IPostEntityProcessingService> iPostEntityProcessingServices;
    private List<IGamePluginService> iGamePluginServices;
    private List<IInputSPI> iInputSPIs;
    private List<IBackgroundComponent> iBackgroundComponents;

    public Game(List<IGraphicsComponent> iGraphicsComponents, List<IEntityProcessingService> iEntityProcessingServices, List<IPostEntityProcessingService> iPostEntityProcessingServices, List<IGamePluginService> iGamePluginServices, List<IInputSPI> iInputSPIs, List<IBackgroundComponent> iBackgroundComponents) {
        this.iGraphicsComponents = iGraphicsComponents;
        this.iEntityProcessingServices = iEntityProcessingServices;
        this.iPostEntityProcessingServices = iPostEntityProcessingServices;
        this.iGamePluginServices = iGamePluginServices;
        this.iInputSPIs = iInputSPIs;
        this.iBackgroundComponents = iBackgroundComponents;
    }

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
