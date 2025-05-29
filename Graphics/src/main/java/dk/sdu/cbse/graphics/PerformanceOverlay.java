package dk.sdu.cbse.graphics;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.ui.IGraphicsService;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PerformanceOverlay implements IGraphicsService {
    private final Runtime runtime = Runtime.getRuntime();
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false;

    private Text fpsText;
    private Text ramText;

    @Override
    public Node createComponent(GameData gameData, World world) {
        Pane performancePane = new Pane();

        fpsText = new Text(10, 35, "FPS:");
        fpsText.setFill(Color.WHITE);
        ramText = new Text(10, 50, "RAM:");
        ramText.setFill(Color.WHITE);

        performancePane.getChildren().addAll(fpsText, ramText);

        return performancePane;
    }

    @Override
    public void updateComponent(GameData gameData, World world) {
        long now = System.nanoTime();
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
            fpsText.setText(String.format("FPS: %.1f", frameRate));
        }

        int usedMemory = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024);
        ramText.setText(String.format("RAM: %d MB", usedMemory));
    }
}
