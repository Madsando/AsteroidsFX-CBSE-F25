package dk.sdu.cbse.common.data.EntityComponents;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class MovementCP implements EntityComponent {
    private double velocity;
    private double rotationSpeed;

    @Override
    public void process(GameData gameData, World world, Entity entity) {

    }
}
