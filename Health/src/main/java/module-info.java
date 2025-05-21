import dk.sdu.cbse.health.*;
import dk.sdu.cbse.common.services.ISystemService;

module Health {
    requires Common;
    requires CommonEntityComponents;

    provides ISystemService with
            HealthSystem;
}


