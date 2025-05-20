package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.NodeSignature;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {
    @Test
    public void canDetectNormalCollision() {
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
        world.update();

        NodeSignature nodeSignature = new NodeSignature(new Class[] {CollisionCP.class,TransformCP.class},null);
        world.addNode(nodeSignature);
        collisionDetectorSystem.update(world.getNodes(nodeSignature),null, world);

        assertEquals(1, collisionCP1.getCollisions().size());
        assertEquals(1, collisionCP2.getCollisions().size());

        assertEquals(entity2.getID(), collisionCP1.getCollisions().peek());
        assertEquals(entity1.getID(), collisionCP2.getCollisions().peek());
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
        world.update();

        NodeSignature nodeSignature = new NodeSignature(new Class[] {CollisionCP.class,TransformCP.class},null);
        world.addNode(nodeSignature);
        collisionDetectorSystem.update(world.getNodes(nodeSignature),null, world);

        assertEquals(0, collisionCP1.getCollisions().size());
        assertEquals(0, collisionCP2.getCollisions().size());
    }

    @Test
    public void canDetectPointCollision() {
        CollisionDetectorSystem collisionDetectorSystem = new CollisionDetectorSystem();
        Entity entity1 = new Entity(0);
        Entity entity2 = new Entity(1);

        CollisionCP collisionCP1 = new CollisionCP();
        CollisionCP collisionCP2 = new CollisionCP();
        entity1.addComponent(collisionCP1);
        entity2.addComponent(collisionCP2);

        assertEquals(0, collisionCP1.getCollisions().size());
        assertEquals(0, collisionCP2.getCollisions().size());

        TransformCP transformCP = new TransformCP(0,0,0,0);
        entity1.addComponent(transformCP);
        entity2.addComponent(transformCP);

        World world = new World();
        world.addEntity(entity1);
        world.addEntity(entity2);
        world.update();

        NodeSignature nodeSignature = new NodeSignature(new Class[] {CollisionCP.class,TransformCP.class},null);
        world.addNode(nodeSignature);
        collisionDetectorSystem.update(world.getNodes(nodeSignature),null, world);

        assertEquals(1, collisionCP1.getCollisions().size());
        assertEquals(1, collisionCP2.getCollisions().size());

        assertEquals(entity2.getID(), collisionCP1.getCollisions().peek());
        assertEquals(entity1.getID(), collisionCP2.getCollisions().peek());
    }

    @Test
    public void canDetectEdgeCollision() {
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
        TransformCP transformCP2 = new TransformCP(0,2,0,1);
        entity1.addComponent(transformCP1);
        entity2.addComponent(transformCP2);

        World world = new World();
        world.addEntity(entity1);
        world.addEntity(entity2);
        world.update();

        NodeSignature nodeSignature = new NodeSignature(new Class[] {CollisionCP.class,TransformCP.class},null);
        world.addNode(nodeSignature);
        collisionDetectorSystem.update(world.getNodes(nodeSignature),null, world);

        assertEquals(1, collisionCP1.getCollisions().size());
        assertEquals(1, collisionCP2.getCollisions().size());

        assertEquals(entity2.getID(), collisionCP1.getCollisions().peek());
        assertEquals(entity1.getID(), collisionCP2.getCollisions().peek());
    }

    @Test
    public void canDetectMultipleCollisionsOnWithEntity() {
        CollisionDetectorSystem collisionDetectorSystem = new CollisionDetectorSystem();
        Entity entity1 = new Entity(0);
        Entity entity2 = new Entity(1);
        Entity entity3 = new Entity(2);

        CollisionCP collisionCP1 = new CollisionCP();
        CollisionCP collisionCP2 = new CollisionCP();
        CollisionCP collisionCP3 = new CollisionCP();
        entity1.addComponent(collisionCP1);
        entity2.addComponent(collisionCP2);
        entity3.addComponent(collisionCP3);

        assertEquals(0, collisionCP1.getCollisions().size());
        assertEquals(0, collisionCP2.getCollisions().size());
        assertEquals(0, collisionCP3.getCollisions().size());

        TransformCP transformCP1 = new TransformCP(0,0,0,1);
        TransformCP transformCP2 = new TransformCP(0,2,0,1);
        TransformCP transformCP3 = new TransformCP(0,-2,0,1);
        entity1.addComponent(transformCP1);
        entity2.addComponent(transformCP2);
        entity3.addComponent(transformCP3);

        World world = new World();
        world.addEntity(entity1);
        world.addEntity(entity2);
        world.addEntity(entity3);
        world.update();

        NodeSignature nodeSignature = new NodeSignature(new Class[] {CollisionCP.class,TransformCP.class},null);
        world.addNode(nodeSignature);
        collisionDetectorSystem.update(world.getNodes(nodeSignature),null, world);

        assertEquals(2, collisionCP1.getCollisions().size());
        assertEquals(1, collisionCP2.getCollisions().size());
        assertEquals(1, collisionCP3.getCollisions().size());

        assertTrue(collisionCP1.getCollisions().contains(entity2.getID()));
        assertTrue(collisionCP1.getCollisions().contains(entity3.getID()));
        assertEquals(entity1.getID(), collisionCP2.getCollisions().peek());
        assertEquals(entity1.getID(), collisionCP3.getCollisions().peek());

    }
}
