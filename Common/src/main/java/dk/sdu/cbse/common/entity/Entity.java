package dk.sdu.cbse.common.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private final Map<Class<? extends EntityComponent>, EntityComponent> entityComponentMap;
    private final EEntityType entityType;

    public Entity(EEntityType entityType) {
        this.entityType = entityType;
        entityComponentMap = new ConcurrentHashMap<>();
    }

    public String getID() {
        return ID.toString();
    }

    public <E extends EntityComponent> E getComponent(Class<E> componentClass) {
        return componentClass.cast(entityComponentMap.get(componentClass));
    }

    public void addComponent(EntityComponent component) {
        entityComponentMap.put(component.getClass(), component);
    }

    public void removeComponent(Class<? extends EntityComponent> componentClass) {
        entityComponentMap.remove(componentClass);
    }

    public void removeComponent(EntityComponent component) {
        entityComponentMap.remove(component.getClass());
    }

    public Collection<EntityComponent> getComponents() {
        return entityComponentMap.values().stream().sorted(new ComponentComparator()).collect(Collectors.toList());
    }

    public EEntityType getEntityType() {
        return entityType;
    }
}
