import dk.sdu.cbse.basicSystems.*;
import dk.sdu.cbse.common.services.ISystemService;

module Movement {
    requires Common;
    requires CommonEntityComponents;
    requires CommonInput;
    requires CommonMovement;

    provides ISystemService with
            MovementSystem,
            MovementInputControlSystem,
            RandomMovementSystem,
            CullingSystem,
            WraparoundSystem;
}


