package dk.sdu.cbse.common.entity;

public enum EEntityType {
    PLAYER, // Destroy if collide with other entity and asteroid. Lose life by bullet
    ENEMY,
    BULLET, // Remove life from others. Split asteroid
    ASTEROID,
    OTHER
}
