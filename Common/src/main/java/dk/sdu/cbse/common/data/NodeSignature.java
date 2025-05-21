package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.services.IEntityComponent;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class NodeSignature {
    Class<? extends IEntityComponent>[] components;
    Class<? extends IEntityComponent>[] optionalComponents;

    public NodeSignature(Class<? extends IEntityComponent>[] components, Class<? extends IEntityComponent>[] optionalComponents) {
        this.components = components;
        this.optionalComponents = optionalComponents;
    }

    public Class<? extends IEntityComponent>[] getComponentSignature() {
        return components;
    }

    public Class<? extends IEntityComponent>[] getOptionalComponentSignature() {
        return optionalComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NodeSignature that)) return false;
        return Objects.deepEquals(components, that.components) && Objects.deepEquals(optionalComponents, that.optionalComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(components), Arrays.hashCode(optionalComponents));
    }
}
