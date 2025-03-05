package dk.sdu.cbse.common.data.EntityComponents;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import java.util.Random;

public class AttackCP implements EntityComponent {
    private int attackChance;
    private int attackCooldown;
    private long lastAttack;
    private boolean shouldAttack;
    private Random rand;

    public AttackCP(int attackChance, int attackCooldown, boolean shouldAttack) {
        this.attackChance = attackChance;
        this.attackCooldown = attackCooldown;
        this.shouldAttack = shouldAttack;
        this.lastAttack = 0;
        rand = new Random();
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        if (shouldAttack & isCooldownOver() & rand.nextInt(attackChance) == 1) {
            this.lastAttack = System.currentTimeMillis();
            //TODO: FIRE BULLET!!!
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
}
