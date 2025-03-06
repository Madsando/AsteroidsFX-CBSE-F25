package dk.sdu.cbse.commoncollision;

public abstract class CollisionPair {
    private final ECollisionType entity;
    private final ECollisionType otherEntity;

    public CollisionPair(ECollisionType entity, ECollisionType otherEntity) {
        this.entity = entity;
        this.otherEntity = otherEntity;
    }

    public ECollisionType[] getEntities() {
        return new ECollisionType[]{entity, otherEntity};
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CollisionPair that = (CollisionPair) o;

        return (entity == that.entity & otherEntity == that.otherEntity ||
                entity == that.otherEntity & otherEntity == that.entity);
    }
}
