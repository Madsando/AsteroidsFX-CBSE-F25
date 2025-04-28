package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.services.IEntityComponent;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private final int typeID;
    private final Map<Class<? extends IEntityComponent>, IEntityComponent> entityComponentMap;

    public Entity(int typeID) {
        this.typeID = typeID;
        entityComponentMap = new ConcurrentHashMap<>();
    }

    public String getID() {
        return ID.toString();
    }

    public <E extends IEntityComponent> E getComponent(Class<E> componentClass) {
        return componentClass.cast(entityComponentMap.get(componentClass));
    }

    public void addComponent(IEntityComponent component) {
        entityComponentMap.put(component.getClass(), component);
    }

    public void removeComponent(Class<? extends IEntityComponent> componentClass) {
        entityComponentMap.remove(componentClass);
    }

    public void removeComponent(IEntityComponent component) {
        entityComponentMap.remove(component.getClass());
    }

    public Collection<IEntityComponent> getComponents() {
        return entityComponentMap.values();
    }

    public int getTypeID() {
        return typeID;
    }
}
