package dk.sdu.cbse.basicSystems;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.entitycomponents.WraparoundCP;
import dk.sdu.cbse.common.services.ISystemService;

public class WraparoundSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void update(GameData gameData, World world) {
        for (Entity entity : world.getEntitiesWithComponent(WraparoundCP.class)) {
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
