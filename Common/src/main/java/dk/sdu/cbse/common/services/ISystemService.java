package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Node;
import dk.sdu.cbse.common.data.NodeSignature;
import dk.sdu.cbse.common.data.World;

import java.util.Collection;

public interface ISystemService {
    NodeSignature getNodeSignature();
    int getPriority();
    void update(Collection<Node> nodes, GameData gameData, World world);
}
