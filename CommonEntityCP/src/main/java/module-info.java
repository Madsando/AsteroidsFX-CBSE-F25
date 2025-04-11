import dk.sdu.cbse.common.bullet.IBulletSPI;

module CommonEntityCP {
    requires Common;
    requires CommonCollision;
    requires CommonBullet;

    exports dk.sdu.cbse.common.entitycomponents;

    uses IBulletSPI;
}


