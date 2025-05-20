package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.services.IEntityComponent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class World {
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private final Map<NodeSignature, Collection<Node>> nodeMap = new ConcurrentHashMap<>();
    private final Queue<Collection<Node>> nodeRemovalQueue = new ConcurrentLinkedQueue<>();
    private final Queue<String> entityRemovalQueue = new ConcurrentLinkedQueue<>();
    private final Queue<Entity> newEntityQueue = new ConcurrentLinkedQueue<>();
    private int nextTypeId = 0;

    public void addEntity(Entity entity) {
        newEntityQueue.add(entity);
    }

    public void removeEntity(String entityID) {
        removeNodesFromEntity(entityMap.get(entityID));
        entityRemovalQueue.add(entityID);
    }

    public void removeEntity(Entity entity) {
        removeNodesFromEntity(entity);
        entityRemovalQueue.add(entity.getID());
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public List<Entity> getEntities(int typeId) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (e.getTypeID() == typeId) {
                r.add(e);
            }
        }
        return r;
    }

    public Collection<Node> getNodes(NodeSignature signature) {
        return nodeMap.get(signature);
    }
    
    public int generateTypeId() {
        nextTypeId++;
        return nextTypeId;
    }

    public void addNode(NodeSignature nodeSignature) {
        nodeMap.putIfAbsent(nodeSignature, createNodeFromAllExistingEntities(nodeSignature));
    }

    public Map<NodeSignature, Collection<Node>> getNodeMap() {
        return nodeMap;
    }

    public void removeNode(NodeSignature nodeSignature) {
        nodeMap.remove(nodeSignature);
    }

    private Collection<Node> createNodeFromAllExistingEntities(NodeSignature nodeSignature) {
        // Go through all entities when adding a new node
        Collection<Node> nodes = new ArrayList<>();
        for (Entity e : getEntities()) {
            getNodeFromEntity(nodeSignature, e).ifPresent(nodes::add);
        }

        return nodes;
    }


    private Collection<Node> getNodesFromEntity(Entity entity) {
        List<Node> nodes = new ArrayList<>();
        for (NodeSignature nodeSignature: nodeMap.keySet()) {
            getNodeFromEntity(nodeSignature, entity).ifPresent(nodes::add);
        }

        return nodes;
    }

    private void createNodesFromEntity(Entity entity) {
        for (NodeSignature nodeSignature: nodeMap.keySet()) {
            Collection<Node> nodes = nodeMap.get(nodeSignature);
            getNodeFromEntity(nodeSignature, entity).ifPresent(nodes::add);
        }
    }

    private void removeNodesFromEntity(Entity entity) {
        Collection<Node> nodes = getNodesFromEntity(entity);
        nodeRemovalQueue.add(nodes);
    }

    public void update() {
        processEntities();
        processNodes();
    }

    private void processNodes() {
        while (!nodeRemovalQueue.isEmpty()) {
            Collection<Node> nodes = nodeRemovalQueue.poll();
            for (Collection<Node> nodeCollection: nodeMap.values()) {
                nodeCollection.removeAll(nodes);
            }
        }
    }

    private void processEntities() {
        // Remove queued entities
        while (!entityRemovalQueue.isEmpty()) {
            entityMap.remove(entityRemovalQueue.poll());
        }

        // Add new entities
        while (!newEntityQueue.isEmpty()) {
            Entity entity = newEntityQueue.poll();
            entityMap.put(entity.getID(), entity);
            createNodesFromEntity(entity);
        }
    }


    private Optional<Node> getNodeFromEntity(NodeSignature nodeSignature, Entity entity) {
        List<IEntityComponent> components = new ArrayList<>();
        List<Optional<IEntityComponent>> optionalComponents = new ArrayList<>();

        for (Class<? extends IEntityComponent> component: nodeSignature.getComponentSignature()) {
            if (entity.getComponent(component) == null) {
                return Optional.empty();
            }
            components.add(entity.getComponent(component));
        }

        if (nodeSignature.getOptionalComponentSignature() != null) {
            for (Class<? extends IEntityComponent> component: nodeSignature.getOptionalComponentSignature()) {
                optionalComponents.add(Optional.ofNullable(entity.getComponent(component)));
            }
        }

        return Optional.of(new Node(entity.getID(), entity.getTypeID(), components, optionalComponents));
    }
}
