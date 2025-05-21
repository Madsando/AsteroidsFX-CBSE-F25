package dk.sdu.cbse.star;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entitycomponents.*;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;
import java.util.Random;

public class FlickeringStarSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{FlickeringShapeCP.class, ShapeCP.class},
                null
        );
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        for (Node node : nodes) {
            ShapeCP shapeCP = (ShapeCP) node.getComponent(ShapeCP.class);

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
