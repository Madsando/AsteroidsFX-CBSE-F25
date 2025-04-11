package dk.sdu.cbse.common.entity;

import java.util.Comparator;

public class ComponentComparator implements Comparator<EntityComponent> {
    @Override
    public int compare(EntityComponent o1, EntityComponent o2) {
        return o1.getPriority() - o2.getPriority();
    }
}
