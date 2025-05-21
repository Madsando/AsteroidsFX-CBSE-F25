package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entitycomponents.CullingCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;

public class CullingSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{CullingCP.class, TransformCP.class},
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

            if (transformCP.getX() < 0 |
                transformCP.getX() > gameData.getDisplayWidth() |
                transformCP.getY() < 0 |
                transformCP.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(node.getEntityID());
            }
        }
    }
}
