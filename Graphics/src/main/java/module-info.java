import dk.sdu.cbse.common.graphics.IBackgroundComponent;
import dk.sdu.cbse.common.graphics.IGraphicsComponent;

import dk.sdu.cbse.graphics.*;

module Graphics {
    requires Common;
    requires CommonGraphics;
    requires javafx.graphics;
    requires java.desktop;

    provides IGraphicsComponent with
            EntityRenderer,
            ScoreOverlay,
            PerformanceOverlay;

    provides IBackgroundComponent with
            SceneBackground;
}


