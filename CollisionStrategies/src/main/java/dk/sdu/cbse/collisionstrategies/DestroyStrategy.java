package dk.sdu.cbse.collisionstrategies;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.HealthCP;
import dk.sdu.cbse.common.utility.UnorderedPair;
import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.collision.ICollisionStrategy;

public class DestroyStrategy implements ICollisionStrategy {
    @Override
    public UnorderedPair<EEntityType>[] getCollisionSignatures() {
        return new UnorderedPair[]{
                new UnorderedPair<>(EEntityType.PLAYER, EEntityType.PLAYER),
                new UnorderedPair<>(EEntityType.ENEMY, EEntityType.ENEMY),
                new UnorderedPair<>(EEntityType.PLAYER, EEntityType.ENEMY),};
    }

    @Override
    public void handleCollision(GameData gamedata, World world, UnorderedPair<Entity> entityPair) {
        HealthCP e = entityPair.getK().getComponent(HealthCP.class);
        e.setHealth(0);

        HealthCP e2 = entityPair.getV().getComponent(HealthCP.class);
        e2.setHealth(0);
    }
}
