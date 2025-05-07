package dk.sdu.cbse.star;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.CullingCP;
import dk.sdu.cbse.common.entitycomponents.FlickeringShapeCP;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.services.IEntityComponent;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Random;

public class FlickeringStarSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void update(GameData gameData, World world) {
        Class<? extends IEntityComponent>[] components = new Class[]{FlickeringStarSystem.class, ShapeCP.class};
        for (Entity e : world.getEntitiesWithComponents(components)) {
            ShapeCP shapeCP = e.getComponent(ShapeCP.class);

            int[] colors =  shapeCP.getColor();
            Random rng = new Random();
            if (rng.nextInt(20) == 1) {
                for (int i = 0; i < colors.length; i++) {
                    colors[i] = (colors[i] + 1) % 150 + 105;
                }
            }
            shapeCP.setColor(colors);
        }

    }
}
