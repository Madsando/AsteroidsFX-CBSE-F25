import dk.sdu.cbse.common.graphics.IBackgroundComponent;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;

import dk.sdu.cbse.graphics.*;

module Graphics {
    requires Common;
    requires CommonEntityComponents;
    requires CommonGraphics;
    requires javafx.graphics;

    provides IGraphicsComponent with
            EntityRenderer,
            ScoreOverlay,
            PerformanceOverlay;

    provides IBackgroundComponent with
            SceneBackground;
}


