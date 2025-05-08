package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.CullingCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.services.IEntityComponent;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.ArrayList;

public class CullingSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void update(GameData gameData, World world) {
        Class<? extends IEntityComponent>[] components = new Class[]{CullingCP.class, TransformCP.class};
        for (Entity e : world.getEntitiesWithComponents(components)) {
            TransformCP transformCP = e.getComponent(TransformCP.class);

            if (transformCP.getX() < 0 |
                transformCP.getX() > gameData.getDisplayWidth() |
                transformCP.getY() < 0 |
                transformCP.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(e);
            }

        }
    }
}
