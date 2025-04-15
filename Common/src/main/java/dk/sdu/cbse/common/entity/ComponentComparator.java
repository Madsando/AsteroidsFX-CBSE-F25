package dk.sdu.cbse.common.entity;

import java.util.Comparator;

public class ComponentComparator implements Comparator<IEntityComponent> {
    @Override
    public int compare(IEntityComponent o1, IEntityComponent o2) {
        return o1.getPriority() - o2.getPriority();
    }
}
