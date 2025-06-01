package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.*;

/**
 * {@code ICustomEntityBehaviour} can be saved in {@link IEntityComponent} depending on the implementation
 * to give the choice of using custom behaviours when processing them in {@link ISystemService}. <br></br>
 *
 * @see IEntityComponent
 * @see ISystemService
 */
public interface ICustomEntityBehaviour {
    /** {@code process} is used execute the behaviour by the method.
     * The method will be called when processing the {@link IEntityComponent} containing it in {@link ISystemService}. <br></br>
     * <b> Pre-conditions </b>
     * <ul>
     *  <li> {@code ICustomEntityBehaviour} has not been processed in this update cycle for the given {@link Entity}</li>
     * </ul> <br>
     * <b> Post-conditions </b>
     * <ul>
     *  <li> Custom behaviour has been executed for the given {@link Entity}</li>
     * </ul>
     *
     * @param gameData Relevant data about the game for the Behaviour to use
     * @param world A container for Entities or Nodes, which the Behaviour can edit
     * @param entity The entity, which is currently being processed
     *
     * @see Entity
     * @see World
     * @see GameData
     */
    void process(GameData gameData, World world, Entity entity);
}
