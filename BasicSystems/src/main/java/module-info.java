import dk.sdu.cbse.basicSystems.*;
import dk.sdu.cbse.common.services.ISystemService;

module BasicSystems {
    requires Common;
    requires CommonEntityCP;

    provides ISystemService with
            HealthSystem,
            MovementSystem,
            MovementInputControlSystem,
            RandomMovementSystem,
            CullingSystem,
            WraparoundSystem;
}


