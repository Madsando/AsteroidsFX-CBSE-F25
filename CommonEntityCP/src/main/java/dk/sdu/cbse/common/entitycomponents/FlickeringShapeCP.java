package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;

import java.util.Random;

public class FlickeringShapeCP implements IEntityComponent {
    @Override
    public void process(GameData gameData, World world, Entity entity) {
        ShapeCP shapeCP = entity.getComponent(ShapeCP.class);

        int[] colors =  shapeCP.getColor();
        Random rng = new Random();
        if (rng.nextInt(20) == 1) {
            for (int i = 0; i < colors.length; i++) {
                colors[i] = (colors[i] + 1) % 150 + 105;
            }
        }
        shapeCP.setColor(colors);
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
