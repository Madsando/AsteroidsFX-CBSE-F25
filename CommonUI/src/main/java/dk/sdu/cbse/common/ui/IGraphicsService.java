package dk.sdu.cbse.common.ui;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Background;

/**
 * {@code IGraphicsService} is used to create and update the graphics of the game. The service returns a {@link Node}
 * which will be added to the scene graph and can be updated. <br></br>
 *
 * Note: The service returns a singular node and if you want to add a collection of {@link Node Nodes} then they should
 * be bundled in a {@link Parent} or subclass to constitute a single {@link Node}.
 *
 * @see Node
 * @see Parent
 */
public interface IGraphicsService {
    /**{@code createComponent} creates a graphical component in the form of a {@link Node} from JavaFX.
     * This {@code Node} can be added to the scene graph of the game and makes the graphics
     * modular. <br></br>
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> This {@link Node} has not been added to the scene graph</li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     * <li> A {@link Background} is created and returned </li>
     *</ul>
     *
     * @return A {@link Node} from JavaFX
     *
     * @see Node
     * @see IGraphicsService
     */
    Node createComponent(GameData gameData, World world);

    /**{@code updateComponent} updates the component created by {@link IGraphicsService#createComponent(GameData, World)}
     * from this instance.<br></br>
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> {@link IGraphicsService#createComponent(GameData, World)} has been called </li>
     *  <li> {@code updateComponent(GameData, World)} gets called on the same instance as the one having created the component</li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     * <li> {@link Node} and sub nodes have been updated </li>
     * <li> GUI has been updated </li>
     *</ul>
     *
     * @see Node
     * @see IGraphicsService
     */
    void updateComponent(GameData gameData, World world);
}
