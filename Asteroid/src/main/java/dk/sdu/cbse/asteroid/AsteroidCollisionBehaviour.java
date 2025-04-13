package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.collision.ICollisionBehaviour;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.HealthCP;

public class AsteroidCollisionBehaviour implements ICollisionBehaviour {
    @Override
    public void process(GameData gameData, World world, Entity entity, EEntityType targetType) {
        HealthCP healthCP = entity.getComponent(HealthCP.class);
        switch (targetType) {
            case ASTEROID, OTHER:
                break;
            case ENEMY, BULLET, PLAYER:
                healthCP.setHealth(0);
                gameData.addScore(1);
                break;
        }
    }
}
