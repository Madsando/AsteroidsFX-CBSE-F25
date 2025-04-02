package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.EntityComponent;

public class CollisionCP implements EntityComponent {
    private EEntityType collisionType;

    public CollisionCP(EEntityType collisionType) {
        this.collisionType = collisionType;
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
    }

    public EEntityType getCollisionType() {
        return this.collisionType;
    }

    public void setCollisionType(EEntityType collisionType) {
        this.collisionType = collisionType;
    }
}
