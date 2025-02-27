package dk.sdu.cbse.commonbullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

public interface IBulletSPI {
    public void createBullet(World world, Entity shooter);
}
