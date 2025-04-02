package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.collision.ICollisionBehaviour;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.entity.CustomEntityBehaviour;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EntityComponent;

public class CollisionCP implements EntityComponent {
    private final ICollisionBehaviour collisionBehaviour;
    private EEntityType targetType;
    private boolean hasCollided = false;


    public CollisionCP(ICollisionBehaviour collisionBehaviour) {
        this.collisionBehaviour = collisionBehaviour;
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        if (hasCollided) {
            collisionBehaviour.setTarget(targetType);
            collisionBehaviour.process(gameData, world, entity);
            hasCollided = false;
        }
    }

    public void setTargetType(EEntityType targetType) {
        this.targetType = targetType;
    }

    public void setHasCollided(boolean hasCollided) {
        this.hasCollided = hasCollided;
    }
}
