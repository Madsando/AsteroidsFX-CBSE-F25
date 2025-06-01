package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.*;

import java.util.Collection;

/**
 * {@code ISystemService} process {@link Node Nodes} from all {@link Entity Entities} using {@link #update(Collection, GameData, World)}.
 *  These processes are used to manipulate the {@link Entity Entities} such as moving them or checking for a collision, to make them
 *  exhibit behaviours <br></br>
 *
 * The game engine contains all the {@code Systems} and puts them in a queue according to their priority gotten from {@link #getPriority()}
 * and it creates the needed {@link Node Nodes} in {@link World}  for the systems based on {@link #getNodeSignature()}
 *
 *
 * @see Entity
 * @see IEntityComponent
 * @see Node
 * @see NodeSignature
 * @see World
 * @see GameData
 */
public interface ISystemService {
    /** {@code getNodeSignature} is used to get the {@link NodeSignature} describing the {@link Node} used by this {@link ISystemService}.
     * The corresponding {@link Node} will later be added to {@link World} by the game engine for use by this {@link ISystemService}. <br></br>
     * <b> Pre-conditions </b>
     * <ul>
     *  <li> {@code update} has not been called in this cycle</li>
     * </ul> <br>
     * <b> Post-conditions </b>
     * <ul>
     *  <li> Returns a {@link NodeSignature} describing a {@link Node}</li>
     * </ul>
     *
     * @return A {@link NodeSignature} describing the {@link Node} used by this {@link ISystemService}
     *
     * @see Node
     * @see NodeSignature
     * @see World
     * @see ISystemService
     */
    NodeSignature getNodeSignature();

    /** {@code getPriority} is used to get the {@link NodeSignature} describing the {@link Node} used by this {@link ISystemService}.
     * The corresponding {@link Node} will later be added to {@link World} by the game engine for use by this {@link ISystemService}. <br></br>
     * <b> Pre-conditions </b>
     * <ul>
     *  <li> System has not been added to the game's processing queue</li>
     * </ul> <br>
     * <b> Post-conditions </b>
     * <ul>
     *  <li> Returns this {@link ISystemService}'s priority</li>
     * </ul>
     *
     * @return An {@code int} describing this system's priority
     *
     * @see ISystemService
     */
    int getPriority();

    /** {@code update} is used to update {@code ISystemService}, which causes it to perform its logic on the given {@link Node Nodes}.
     * The given {@code Nodes}' signatures should match the required {@link NodeSignature} by this {@code ISystemService}
     * as defined by {@link ISystemService#getNodeSignature()}. <br></br>
     * <b> Pre-conditions </b>
     * <ul>
     *  <li> The ISystemService has not been updated this cycle </li>
     *  <li> Nodes' signatures should match the Systems required node-structure </li>
     *  <li> Game is running </li>
     *  <li> Systems with higher priorities has been updated </li>
     * </ul> <br>
     * <b> Post-conditions </b>
     * <ul>
     *  <li> ISystemService has updated and executed its logic </li>
     * </ul>
     *
     * @param nodes A collection of {@link Node Nodes} that contains {@link IEntityComponent IEntityComponents} from {@link Entity Entities} to be processed
     * @param gameData Relevant data about the game for the System to use
     * @param world A container for Entities or Nodes, which the System can edit
     *
     * @see Node
     * @see NodeSignature
     * @see IEntityComponent
     * @see ISystemService
     * @see GameData
     * @see World
     */
    void update(Collection<Node> nodes, GameData gameData, World world);
}
