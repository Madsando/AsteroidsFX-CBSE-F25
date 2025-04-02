package dk.sdu.cbse.common.entity;

public enum EEntityType {
    ENTITY, // Destroy if collide with other entity and asteroid. Lose life by bullet
    BULLET, // Remove life from others. Split asteroid
    ASTEROID
}
