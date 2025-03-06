import dk.sdu.cbse.commonbullet.IBulletSPI;

module CommonBulletCP {
    requires Common;
    requires CommonBullet;

    exports dk.sdu.cbse.commonbulletcp;

    uses IBulletSPI;
}


