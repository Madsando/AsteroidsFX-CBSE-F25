import dk.sdu.cbse.basicSystems.HealthSystem;
import dk.sdu.cbse.common.services.ISystemService;
import dk.sdu.cbse.basicSystems.MovementInputControlSystem;
import dk.sdu.cbse.basicSystems.MovementSystem;
import dk.sdu.cbse.basicSystems.RandomMovementSystem;

module BasicSystems {
    requires Common;
    requires CommonEntityCP;

    provides ISystemService with
            HealthSystem,
            MovementSystem,
            MovementInputControlSystem,
            RandomMovementSystem;
}


