package dk.sdu.cbse.commonbullet;

import dk.sdu.cbse.common.data.Entity;

public interface IBulletSPI {
    public Entity createBullet(Entity shooter);
}
