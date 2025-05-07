package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.CullingCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.entitycomponents.WraparoundCP;
import dk.sdu.cbse.common.services.IEntityComponent;
import dk.sdu.cbse.common.services.ISystemService;

public class WraparoundSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void update(GameData gameData, World world) {
        Class<? extends IEntityComponent>[] components = new Class[]{WraparoundSystem.class, TransformCP.class};
        for (Entity entity : world.getEntitiesWithComponents(components)) {
            TransformCP transformCP = entity.getComponent(TransformCP.class);

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
