package dk.sdu.cbse.player;

import dk.sdu.cbse.common.collision.ICollisionBehaviour;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entitycomponents.HealthCP;

public class PlayerCollisionBehaviour implements ICollisionBehaviour {
    private EEntityType targetEntityType;

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        HealthCP healthCP = entity.getComponent(HealthCP.class);
        switch (targetEntityType) {
            case OTHER:
            case null:
                break;
            case ENEMY, PLAYER, ASTEROID:
                healthCP.setHealth(0);
                break;
            case BULLET:
                healthCP.subtractHealth(1);
                break;
        }
    }

    @Override
    public void setTarget(EEntityType target) {
        this.targetEntityType = target;
    }
}
