package dk.sdu.cbse.common.bullet;

import dk.sdu.cbse.common.services.IEntityComponent;

public class BulletCP implements IEntityComponent {
    private int attackChance;
    private int attackCooldown;
    private long lastAttack;
    private boolean shouldAttack;

    public BulletCP(int attackChance, int attackCooldown, boolean shouldAttack) {
        this.attackChance = attackChance;
        this.attackCooldown = attackCooldown;
        this.shouldAttack = shouldAttack;
        this.lastAttack = 0;
    }

    public long getLastAttack() {
        return lastAttack;
    }

    public void setLastAttack(long lastAttack) {
        this.lastAttack = lastAttack;
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
