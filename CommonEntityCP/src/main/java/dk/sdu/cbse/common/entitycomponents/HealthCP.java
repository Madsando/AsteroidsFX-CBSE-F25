package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.entity.ICustomEntityBehaviour;
import dk.sdu.cbse.common.entity.IEntityComponent;

public class HealthCP implements IEntityComponent {
    private int health;
    private final ICustomEntityBehaviour behaviour;

    public HealthCP(int health, ICustomEntityBehaviour behaviour) {
        this.health = health;
        this.behaviour = behaviour;
    }

    public ICustomEntityBehaviour getBehaviour() {
        return behaviour;
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
