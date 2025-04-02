package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.collision.ICollisionBehaviour;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.HealthCP;

public class AsteroidCollisionBehaviour implements ICollisionBehaviour {
    private EEntityType targetEntityType;

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        switch (targetEntityType) {
            case ASTEROID, OTHER:
            case null:
                break;
            default:
                HealthCP healthCP = entity.getComponent(HealthCP.class);
                healthCP.setHealth(0);
                gameData.addScore(1);
        }
    }

    @Override
    public void setTarget(EEntityType target) {
        this.targetEntityType = target;
    }
}
