package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.IBulletSPI;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entitycomponents.BulletCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class BulletSystem implements ISystemService {
    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public void update(GameData gameData, World world) {
        Random rand = new Random();
        for (Entity e : world.getEntitiesWithComponent(BulletCP.class)) {
            BulletCP bulletCP = e.getComponent(BulletCP.class);

            if (bulletCP.shouldAttack()
                    & isCooldownOver(bulletCP.getAttackCooldown(), bulletCP.getLastAttack())
                    & rand.nextInt(bulletCP.getAttackChance()) == 0) {
                bulletCP.setLastAttack(System.currentTimeMillis());

                getIBulletSPI().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(e))
                );
            }
        }
    }

    private boolean isCooldownOver(double lastAttack, double attackCooldown) {
        return (System.currentTimeMillis() - lastAttack > attackCooldown);
    }

    private Collection<? extends IBulletSPI> getIBulletSPI() {
        return ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
