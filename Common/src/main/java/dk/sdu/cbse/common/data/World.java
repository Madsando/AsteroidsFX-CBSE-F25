package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.entity.EEntityType;
import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.entity.IEntityComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

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

    public List<Entity> getEntities(EEntityType entityType) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (e.getEntityType() == entityType) {
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

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }
}
