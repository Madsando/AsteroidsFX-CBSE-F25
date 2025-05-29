import dk.sdu.cbse.common.ui.IBackgroundService;
import dk.sdu.cbse.common.ui.IGraphicsService;

import dk.sdu.cbse.graphics.*;

module Graphics {
    requires Common;
    requires CommonEntityComponents;
    requires CommonUI;
    requires javafx.graphics;

    provides IGraphicsService with
            EntityRenderer,
            ScoreOverlay,
            PerformanceOverlay;

    provides IBackgroundService with
            SceneBackground;
}


