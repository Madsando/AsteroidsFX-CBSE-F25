package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.collision.ECollisionType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EntityComponent;

public class CollisionCP implements EntityComponent {
    private ECollisionType collisionType;

    public CollisionCP(ECollisionType collisionType) {
        this.collisionType = collisionType;
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
    }

    public ECollisionType getCollisionType() {
        return this.collisionType;
    }

    public void setCollisionType(ECollisionType collisionType) {
        this.collisionType = collisionType;
    }
}
