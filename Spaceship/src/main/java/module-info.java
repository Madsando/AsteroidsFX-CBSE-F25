import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Spaceship {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.mmmi.cbse.spaceship.SpaceshipPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.spaceship.SpaceshipProcessor;

}
