import dk.sdu.cbse.common.bullet.IBulletSPI;

module CommonBulletCP {
    requires Common;
    requires CommonBullet;

    exports dk.sdu.cbse.common.bulletcp;

    uses IBulletSPI;
}


