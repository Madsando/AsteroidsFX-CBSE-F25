package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entitycomponents.CollisionCP;
import dk.sdu.cbse.common.entitycomponents.TransformCP;
import dk.sdu.cbse.common.services.ISystemService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollisionDetectorSystem implements ISystemService {
    private final Set<String> collidedEntities = Collections.synchronizedSet(new HashSet<>());

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public NodeSignature getNodeSignature() {
        return new NodeSignature(
                new Class[]{CollisionCP.class, TransformCP.class},
                null
        );
    }

    @Override
    public void update(Collection<Node> nodes, GameData gameData, World world) {
        for (Node n1 : nodes) {
            collidedEntities.add(n1.getEntityID());

            for (Node n2 : nodes) {
                // Make sure entities are different. Cannot collide with oneself
                // Make sure collisions with the selected entity has not been checked already
                if (n1.getEntityID().equals(n2.getEntityID()) | collidedEntities.contains(n2.getEntityID())) {
                    continue;
                }

                if (entitiesCollide(n1, n2)) {
                    resolveCollision(n1, n2);
                }
            }
        }
        collidedEntities.clear();
    }

    private Boolean entitiesCollide(Node n1, Node n2) {
        // Using Pythagoras' distance formula
        TransformCP eTransformCP = (TransformCP) n1.getComponent(TransformCP.class);
        TransformCP e2TransformCP = (TransformCP) n2.getComponent(TransformCP.class);

        double xDistance = eTransformCP.getX() - e2TransformCP.getX();
        double yDistance = eTransformCP.getY() - e2TransformCP.getY();
        double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);

        return distance <= (eTransformCP.getRadius() + e2TransformCP.getRadius());
    }

    private void resolveCollision(Node n1, Node n2) {
        CollisionCP n1CollisionCP = (CollisionCP) n1.getComponent(CollisionCP.class);
        CollisionCP n2CollisionCP = (CollisionCP) n2.getComponent(CollisionCP.class);

        n1CollisionCP.addCollision(n2.getEntityID());
        n2CollisionCP.addCollision(n1.getEntityID());
    }
}
