package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;
import dk.sdu.cbse.common.services.*;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Pane gameWindow = new Pane();
    private final ArrayList<IGraphicsComponent> graphicsComponents = new ArrayList<>();

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
        ModuleConfig.getIInputService().stream().findFirst().ifPresent(service -> scene.setOnKeyPressed(service.getInputHandlerPress(gameData)));
        ModuleConfig.getIInputService().stream().findFirst().ifPresent(service -> scene.setOnKeyReleased(service.getInputHandlerRelease(gameData)));

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : ModuleConfig.getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }

        graphicsComponents.addAll(ModuleConfig.getIGraphicComponents());
        for (IGraphicsComponent graphicsComponent : graphicsComponents) {
            gameWindow.getChildren().add(graphicsComponent.createComponent(gameData, world));
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
        for (ISystemService systemService: ModuleConfig.getISystemServices()) {
            systemService.update(gameData, world);
        }
    }

    private void draw() {
        for (IGraphicsComponent graphicsComponent : graphicsComponents) {
            graphicsComponent.updateComponent(gameData, world);
        }
    }
}
