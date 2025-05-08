package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.input.EGameInputs;
import dk.sdu.cbse.common.entitycomponents.BulletCP;
import dk.sdu.cbse.common.entitycomponents.InputBulletControlCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;

public class BulletInputControlSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{InputBulletControlCP.class, BulletCP.class},
                null
        );
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        for (Node node: nodes) {
            BulletCP bulletCP = (BulletCP) node.getComponent(BulletCP.class);
            bulletCP.setShouldAttack(gameData.getInputs().isDown(EGameInputs.ACTION));
        }
    }
}
