package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.entitycomponents.CullingCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.input.EGameInputs;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.BulletCP;
import dk.sdu.cbse.common.entitycomponents.InputBulletControlCP;
import dk.sdu.cbse.common.services.IEntityComponent;
import dk.sdu.cbse.common.services.ISystemService;

public class BulletInputControlSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void update(GameData gameData, World world) {
        Class<? extends IEntityComponent>[] components = new Class[]{InputBulletControlCP.class, BulletCP.class};
        for (Entity e : world.getEntitiesWithComponents(components)) {
            BulletCP bulletCP = e.getComponent(BulletCP.class);
            bulletCP.setShouldAttack(gameData.getInputs().isDown(EGameInputs.ACTION));
        }
    }
}
