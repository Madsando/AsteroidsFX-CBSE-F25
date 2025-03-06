package dk.sdu.cbse.commonbulletcp;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.EntityComponent;
import dk.sdu.cbse.commonbullet.IBulletSPI;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class BulletCP implements EntityComponent {
    private int attackChance;
    private int attackCooldown;
    private long lastAttack;
    private boolean shouldAttack;
    private Random rand;

    public BulletCP(int attackChance, int attackCooldown, boolean shouldAttack) {
        this.attackChance = attackChance;
        this.attackCooldown = attackCooldown;
        this.shouldAttack = shouldAttack;
        this.lastAttack = 0;
        rand = new Random();
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        if (shouldAttack & isCooldownOver() & rand.nextInt(attackChance) == 0) {
            this.lastAttack = System.currentTimeMillis();

            getIBulletSPI().stream().findFirst().ifPresent(
                    spi -> world.addEntity(spi.createBullet(entity))
            );
        }
    }

    private boolean isCooldownOver() {
        return (System.currentTimeMillis() - this.lastAttack > this.attackCooldown);
    }

    public boolean shouldAttack() {
        return shouldAttack;
    }

    public void setShouldAttack(boolean shouldAttack) {
        this.shouldAttack = shouldAttack;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public int getAttackChance() {
        return attackChance;
    }

    public void setAttackChance(int attackChance) {
        this.attackChance = attackChance;
    }

    private Collection<? extends IBulletSPI> getIBulletSPI() {
        return ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
