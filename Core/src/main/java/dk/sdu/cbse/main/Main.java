package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private Text fpsText;
    private Text ramText;
    private final Runtime runtime = Runtime.getRuntime();
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false ;

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        fpsText = new Text(10, 35, "FPS:");
        ramText = new Text(10, 50, "RAM:");
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(text);
        gameWindow.getChildren().add(fpsText);
        gameWindow.getChildren().add(ramText);

        Scene scene = new Scene(gameWindow);

        if (ModuleConfig.getIInputService().stream().findFirst().isPresent()) {
            scene.setOnKeyPressed(
                    ModuleConfig.getIInputService().stream().findFirst().get().getInputHandlerPress(gameData)
            );

            scene.setOnKeyReleased(
                    ModuleConfig.getIInputService().stream().findFirst().get().getInputHandlerRelease(gameData)
            );
        }

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : ModuleConfig.getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        render();
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    private void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameData.getInputs().update();
                update();
                draw();
                drawPerformance(now);
            }
        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : ModuleConfig.getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : ModuleConfig.getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }       
    }

    private void draw() {        
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){   
                Polygon removedPolygon = polygons.get(polygonEntity);               
                polygons.remove(polygonEntity);                      
                gameWindow.getChildren().remove(removedPolygon);
            }
        }
                
        for (Entity entity : world.getEntities()) {                      
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }
    }

    private void drawPerformance(long now) {
        long oldFrameTime = frameTimes[frameTimeIndex] ;
        frameTimes[frameTimeIndex] = now ;
        frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
        if (frameTimeIndex == 0) {
            arrayFilled = true ;
        }
        if (arrayFilled) {
            long elapsedNanos = now - oldFrameTime ;
            long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
            double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
            fpsText.setText(String.format("Current frame rate: %.1f", frameRate));
        }

        int usedMemory = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024);
        ramText.setText(String.format("RAM: %d MB", usedMemory));
    }
}
