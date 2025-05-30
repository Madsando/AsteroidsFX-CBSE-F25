package dk.sdu.cbse.common.utility;

import dk.sdu.cbse.common.services.ISystemService;

import java.util.Comparator;

public class ISystemComparator implements Comparator<ISystemService> {
    @Override
    public int compare(ISystemService o1, ISystemService o2) {
        return o1.getPriority() - o2.getPriority();
    }
}
