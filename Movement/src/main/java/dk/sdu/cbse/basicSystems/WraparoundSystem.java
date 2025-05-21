package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.movement.WraparoundCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;

public class WraparoundSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{TransformCP.class, WraparoundCP.class},
                null
        );
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        for (Node node: nodes) {
            TransformCP transformCP = (TransformCP) node.getComponent(TransformCP.class);

            if (transformCP.getX() < 0) {
                transformCP.setX(gameData.getDisplayWidth());
            } else if (transformCP.getX() > gameData.getDisplayWidth()) {
                transformCP.setX(0);
            } else if (transformCP.getY() < 0) {
                transformCP.setY(gameData.getDisplayHeight());
            } else if (transformCP.getY() > gameData.getDisplayHeight()) {
                transformCP.setY(0);
            }
        }

    }
}
