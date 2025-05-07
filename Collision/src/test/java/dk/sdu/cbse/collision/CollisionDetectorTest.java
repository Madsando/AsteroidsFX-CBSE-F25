package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {
    @Test
    public void canDetectCollision() {
        CollisionDetectorSystem collisionDetectorSystem = new CollisionDetectorSystem();
        Entity entity1 = new Entity(0);
        Entity entity2 = new Entity(1);

        CollisionCP collisionCP1 = new CollisionCP();
        CollisionCP collisionCP2 = new CollisionCP();
        entity1.addComponent(collisionCP1);
        entity2.addComponent(collisionCP2);

        assertEquals(0, collisionCP1.getCollisions().size());
        assertEquals(0, collisionCP2.getCollisions().size());

        TransformCP transformCP = new TransformCP(0,0,0,1);
        entity1.addComponent(transformCP);
        entity2.addComponent(transformCP);

        World world = new World();
        world.addEntity(entity1);
        world.addEntity(entity2);

        collisionDetectorSystem.update(null, world);

        assertEquals(1, collisionCP1.getCollisions().size());
        assertEquals(1, collisionCP2.getCollisions().size());

        assertEquals(entity2, collisionCP1.getCollisions().peek());
        assertEquals(entity1, collisionCP2.getCollisions().peek());
    }

    @Test
    public void canDetectNoCollision() {
        CollisionDetectorSystem collisionDetectorSystem = new CollisionDetectorSystem();
        Entity entity1 = new Entity(0);
        Entity entity2 = new Entity(1);

        CollisionCP collisionCP1 = new CollisionCP();
        CollisionCP collisionCP2 = new CollisionCP();
        entity1.addComponent(collisionCP1);
        entity2.addComponent(collisionCP2);

        assertEquals(0, collisionCP1.getCollisions().size());
        assertEquals(0, collisionCP2.getCollisions().size());

        TransformCP transformCP1 = new TransformCP(0,0,0,1);
        TransformCP transformCP2 = new TransformCP(0,3,0,1);
        entity1.addComponent(transformCP1);
        entity2.addComponent(transformCP2);

        World world = new World();
        world.addEntity(entity1);
        world.addEntity(entity2);

        collisionDetectorSystem.update(null, world);

        assertEquals(0, collisionCP1.getCollisions().size());
        assertEquals(0, collisionCP2.getCollisions().size());
    }
}
