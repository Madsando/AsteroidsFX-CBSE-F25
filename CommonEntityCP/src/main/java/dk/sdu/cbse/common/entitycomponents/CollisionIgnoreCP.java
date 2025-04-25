package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.entity.IEntityComponent;

public class CollisionIgnoreCP implements IEntityComponent {
    private final int id;
    private final int[] ignoreCollisionsWithIds;

    public CollisionIgnoreCP(int id, int... ignoreCollisionsWithIds) {
        this.id = id;
        this.ignoreCollisionsWithIds = ignoreCollisionsWithIds;
    }

    public int getId() {
        return id;
    }

    public int[] getIgnoreCollisionsWithIds() {
        return ignoreCollisionsWithIds;
    }
}
