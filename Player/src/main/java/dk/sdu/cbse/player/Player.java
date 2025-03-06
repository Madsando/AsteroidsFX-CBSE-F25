package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;

public class Player extends Entity {
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
