package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.services.IEntityComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {
    private static World instance = null;

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private int nextTypeId = 0;

    private World() {}

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public void addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
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

    public <E extends IEntityComponent> List<Entity> getEntitiesWithComponent(Class<E> entityComponent) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (e.getComponent(entityComponent) != null) {
                r.add(e);
            }
        }
        return r;
    }

    public List<Entity> getEntitiesWithComponents(Class<? extends IEntityComponent>[] entityComponents) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {

            boolean hasAllComponents = true;
            for (Class<? extends IEntityComponent> entityComponent : entityComponents) {
                if (e.getComponent(entityComponent) == null) {
                    hasAllComponents = false;
                    break;
                }
            }
            if (hasAllComponents) {
                r.add(e);
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

    public int generateTypeId() {
        nextTypeId++;
        return nextTypeId;
    }
}
