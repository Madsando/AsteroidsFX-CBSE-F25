package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entity.CustomEntityBehaviour;
import dk.sdu.cbse.common.entity.EntityComponent;

public class HealthCP implements EntityComponent {
    private int health;
    private CustomEntityBehaviour behaviour;

    public HealthCP(int health, CustomEntityBehaviour behaviour) {
        this.health = health;
        this.behaviour = behaviour;
    }

    @Override
    public void process(GameData gameData, World world, Entity entity) {
        if (isDead()) {
            if (behaviour != null) {
                behaviour.process(gameData, world, entity);
            }
            world.removeEntity(entity);
        }
    }

    @Override
    public int getPriority() {
        return 2;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void subtractHealth(int health) {
        this.health -= health;
    }

    public void addHealth(int health) {
        this.health += health;
    }
}
