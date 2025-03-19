package dk.sdu.cbse.star;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entitycomponents.ShapeCP;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class StarProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity star : world.getEntities(Star.class)) {
            ShapeCP shapeCP = star.getComponent(ShapeCP.class);

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
