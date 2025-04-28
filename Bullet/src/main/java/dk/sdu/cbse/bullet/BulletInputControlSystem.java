package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.input.EGameInputs;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.BulletCP;
import dk.sdu.cbse.common.entitycomponents.InputBulletControlCP;
import dk.sdu.cbse.common.services.ISystemService;

public class BulletInputControlSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void update(GameData gameData, World world) {
        for (Entity e : world.getEntitiesWithComponent(InputBulletControlCP.class)) {
            BulletCP bulletCP = e.getComponent(BulletCP.class);
            if (bulletCP != null) {
                bulletCP.setShouldAttack(gameData.getInputs().isDown(EGameInputs.ACTION));
            }
        }
    }
}
