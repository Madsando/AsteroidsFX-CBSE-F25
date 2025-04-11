package dk.sdu.cbse.common.collision;

import dk.sdu.cbse.common.entity.CustomEntityBehaviour;
import dk.sdu.cbse.common.entity.EEntityType;

public interface ICollisionBehaviour extends CustomEntityBehaviour {
    void setTarget(EEntityType target);
}
