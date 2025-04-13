package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.collision.ICollisionBehaviour;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.HealthCP;

public class BulletCollisionBehaviour implements ICollisionBehaviour {
    @Override
    public void process(GameData gameData, World world, Entity entity, EEntityType targetType) {
        switch (targetType) {
            case BULLET, OTHER:
                break;
            default:
                HealthCP healthCP = entity.getComponent(HealthCP.class);
                healthCP.setHealth(0);
                break;
        }
    }
}
