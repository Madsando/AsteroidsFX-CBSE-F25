package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;

public class Enemy extends Entity {
    private long lastAttack;
    private long cooldown;

    public long getLastAttack() {
        return lastAttack;
    }

    public void setLastAttack(long lastAttack) {
        this.lastAttack = lastAttack;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }
}
