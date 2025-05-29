package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.ui.IGraphicsService;
import dk.sdu.cbse.common.services.*;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private final GameData gameData = GameData.getInstance();
    private final World world = World.getInstance();
    private final Pane gameWindow = new Pane();
    private final List<IGraphicsService> graphicsComponents = new ArrayList<>();
    private final List<ISystemService> systemServices = new ArrayList<>();

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) {
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        Scene scene = new Scene(gameWindow);

        gameWindow.heightProperty().addListener((observable, oldValue, newValue) -> gameData.setDisplayHeight(newValue.intValue()));
        gameWindow.widthProperty().addListener((observable, oldValue, newValue) -> gameData.setDisplayWidth(newValue.intValue()));

        ModuleConfig.getIBackgroundComponents().stream().findFirst().ifPresent(backgroundComponent -> gameWindow.setBackground(backgroundComponent.getBackground()));
        ModuleConfig.getIInputService().stream().findFirst().ifPresent(service -> scene.setOnKeyPressed(service.getInputHandlerPress(gameData.getInputs())));
        ModuleConfig.getIInputService().stream().findFirst().ifPresent(service -> scene.setOnKeyReleased(service.getInputHandlerRelease(gameData.getInputs())));

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : ModuleConfig.getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }

        graphicsComponents.addAll(ModuleConfig.getIGraphicComponents());
        for (IGraphicsService graphicsComponent : graphicsComponents) {
            gameWindow.getChildren().add(graphicsComponent.createComponent(gameData, world));
        }

        systemServices.addAll(ModuleConfig.getISystemServices());
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
        for (IGraphicsService graphicsComponent : graphicsComponents) {
            graphicsComponent.updateComponent(gameData, world);
        }
    }
}
