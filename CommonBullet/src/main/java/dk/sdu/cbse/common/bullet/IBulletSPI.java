package dk.sdu.cbse.common.bullet;

import dk.sdu.cbse.common.entity.Entity;

public interface IBulletSPI {
    Entity createBullet(Entity shooter);
}
