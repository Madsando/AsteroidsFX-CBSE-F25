package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.services.IEntityComponent;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Node {
    private final String entityID;
    private final List<IEntityComponent> components;
    private final List<Optional<IEntityComponent>> optionalComponents;

    public Node(String entityID, List<IEntityComponent> components, List<Optional<IEntityComponent>> optionalComponents) {
        this.components = components;
        this.optionalComponents = optionalComponents;
        this.entityID = entityID;
    }

    public String getEntityID() {
        return entityID;
    }

    public IEntityComponent getComponent(Class<? extends IEntityComponent> componentClass) {
        return components.stream().filter(component -> componentClass.isAssignableFrom(component.getClass())).findFirst().orElse(null);
    }

    public Optional<IEntityComponent> getOptionalComponent(Class<? extends IEntityComponent> componentClass) {
        return optionalComponents.stream().filter(component -> componentClass.isAssignableFrom(component.getClass())).findFirst().orElse(null);
    }

    public NodeSignature getNodeSignature() {
        return new NodeSignature((Class<? extends IEntityComponent>[]) components.toArray(), (Class<? extends IEntityComponent>[]) optionalComponents.toArray());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node node)) return false;
        return Objects.equals(entityID, node.entityID) && Objects.equals(components, node.components) && Objects.equals(optionalComponents, node.optionalComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityID, components, optionalComponents);
    }
}
