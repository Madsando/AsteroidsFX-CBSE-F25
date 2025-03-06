import dk.sdu.cbse.commonbullet.IBulletSPI;

module CommonEntityPart {
    requires Common;
    requires CommonBullet;

    uses IBulletSPI;

    exports dk.sdu.cbse.commonentitypart;
}