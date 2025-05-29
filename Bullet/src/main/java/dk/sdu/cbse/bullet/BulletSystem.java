package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.IBulletService;
import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.bullet.BulletCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class BulletSystem implements ISystemService {
    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{BulletCP.class},
                null
        );
    }

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public void update(Collection<Node> nodes,GameData gameData, World world) {
        Random rand = new Random();

        for (Node node: nodes) {
            BulletCP bulletCP = (BulletCP) node.getComponent(BulletCP.class);

            if (bulletCP.shouldAttack()
                    & isCooldownOver(bulletCP.getAttackCooldown(), bulletCP.getLastAttack())
                    & rand.nextInt(bulletCP.getAttackChance()) == 0) {
                bulletCP.setLastAttack(System.currentTimeMillis());

                getIBulletSPI().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(world.getEntity(node.getEntityID())))
                );
            }
        }
    }

    private boolean isCooldownOver(double lastAttack, double attackCooldown) {
        return (System.currentTimeMillis() - lastAttack > attackCooldown);
    }

    private Collection<? extends IBulletService> getIBulletSPI() {
        return ServiceLoader.load(IBulletService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
