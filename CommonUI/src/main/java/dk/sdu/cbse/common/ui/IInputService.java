package dk.sdu.cbse.common.ui;

import dk.sdu.cbse.common.input.GameInputs;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Background;

/**
 * {@code IInputService} is used to create and retrieve input-handlers. They decouple the input actions
 * from the actual user input. The interface is based on key- and mouse-input, so it needs
 * to know what happens when a key is pressed and released. <br></br>
 *
 * @see InputEvent
 * @see EventHandler
 */
public interface IInputService {
    /**{@code getInputHandlerPress} gets an EventHandler that handles input-events generated from the user,
     * specifically what happens when a key/mouse is pressed. <br></br>
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> Game has a scene-graph to connect an EventHandler to </li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     * <li> An EventHandler has been created and retrieved </li>
     *</ul>
     *
     * @param gameInputs Contains the game's input actions, which the Handler can modify
     *
     * @return An {@link EventHandler} from JavaFX
     *
     * @see InputEvent
     * @see EventHandler
     */
    EventHandler<InputEvent> getInputHandlerPress(GameInputs gameInputs);

    /**{@code getInputHandlerPress} gets an EventHandler that handles input-events generated from the user,
     * specifically what happens when a key/mouse is released. <br></br>
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> Game has a scene-graph to connect an EventHandler to </li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     * <li> An EventHandler has been created and retrieved </li>
     *</ul>
     *
     * @param gameInputs Contains the game's input actions, which the Handler can modify
     *
     * @return An {@link EventHandler} from JavaFX
     *
     * @see InputEvent
     * @see EventHandler
     */
    EventHandler<InputEvent> getInputHandlerRelease(GameInputs gameInputs);
}
