package dk.sdu.cbse.common.entitycomponents;

import dk.sdu.cbse.common.services.IEntityComponent;

public class DamageCP  implements IEntityComponent {
    private final int damage;

    public DamageCP(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
