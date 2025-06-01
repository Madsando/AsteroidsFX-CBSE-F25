package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.ui.*;
import dk.sdu.cbse.common.services.*;

import java.util.List;

import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.ui.IGraphicsService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public class Game {
    private final GameData gameData = GameData.getInstance();
    private final World world = World.getInstance();
    private final Pane gameWindow = new Pane();

    @Autowired
    private List<IGraphicsService> graphicsservices;

    @Autowired
    private List<ISystemService> systemServices;

    @Autowired
    private List<IGamePluginService> gamePluginServices;

    @Autowired
    private List<IInputService> inputServices;

    @Autowired
    private List<IBackgroundService> backgroundServices;

    public void start(Stage window) throws Exception {
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        Scene scene = new Scene(gameWindow);

        gameWindow.heightProperty().addListener((observable, oldValue, newValue) -> {
            gameData.setDisplayHeight(newValue.intValue());
        });
        gameWindow.widthProperty().addListener((observable, oldValue, newValue) -> {
            gameData.setDisplayWidth(newValue.intValue());
        });

        gameWindow.setBackground(backgroundServices.getFirst().getBackground());
        scene.setOnKeyPressed(inputServices.getFirst().getInputHandlerPress(gameData.getInputs()));
        scene.setOnKeyReleased(inputServices.getFirst().getInputHandlerRelease(gameData.getInputs()));

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : gamePluginServices) {
            iGamePlugin.start(gameData, world);
        }

        for (IGraphicsService graphicsComponent : graphicsservices) {
            gameWindow.getChildren().add(graphicsComponent.createComponent(gameData, world));
        }

        for (ISystemService iSystemService : systemServices) {
            world.addNode(iSystemService.getNodeSignature());
        }

        render();
        window.setScene(scene);
        window.setTitle("Asteroids");
        window.show();
    }

    private void render() {
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
        for (ISystemService systemService: systemServices) {
            systemService.update(world.getNodes(systemService.getNodeSignature()), gameData, world);
        }
        world.update();
    }

    private void draw() {
        for (IGraphicsService graphicsComponent : graphicsservices) {
            graphicsComponent.updateComponent(gameData, world);
        }
    }
}
