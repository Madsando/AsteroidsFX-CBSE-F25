package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.data.EGameInputs;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;

public class InputBulletControlCP implements IEntityComponent {
    @Override
    public void process(GameData gameData, World world, Entity entity) {
        BulletCP bulletCP = entity.getComponent(BulletCP.class);
        if (bulletCP != null) {
            bulletCP.setShouldAttack(gameData.getInputs().isDown(EGameInputs.ACTION));
        }
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
