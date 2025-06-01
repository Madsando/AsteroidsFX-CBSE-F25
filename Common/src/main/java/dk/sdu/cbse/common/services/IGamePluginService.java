package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * IGamePluginService is used for implementing plugins that extend the game's functionality or content.
 */
public interface IGamePluginService {
    /**{@code start} is used to set up and run a plugin e.g. adding an Entity. The method gets called when the game initializes. <br></br>
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> The plugin is not active </li>
     *  <li> {@code gameData} is not null </li>
     *  <li> {@code world} is not null </li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     *  <li> The plugin's functionality/content has been added to the game </li>
     *  <li> The plugin is started </li>
     * </ul>
     *
     * @param gameData Relevant data about the game for the Plugin to use.
     * @param world A container for Entities or Nodes, which the Plugin can edit
     *
     * @see GameData
     * @see World
     */
    void start(GameData gameData, World world);

    /**{@code stop} is used to stop and safely destroy a plugin. The method gets called when the game stops <br></br>
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> The plugin is active </li>
     *  <li> {@code gameData} is not null </li>
     *  <li> {@code world} is not null </li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     * <li> The plugin has removed its functionality/content from the game </li>
     * <li> The plugin is stopped </li>
     *</ul>
     *
     * @param gameData Relevant data about the game for the Plugin to use
     * @param world A container for Entities or Nodes, which the Plugin can edit
     *
     * @see GameData
     * @see World
     */
    void stop(GameData gameData, World world);
}
