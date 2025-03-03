package dk.sdu.cbse.commoncollision;

public enum ECollisionType {
    ENTITY, // Destroy if collide with other entity and asteroid. Lose life by bullet
    BULLET, // Remove life from others. Split asteroid
    ASTEROID
}
