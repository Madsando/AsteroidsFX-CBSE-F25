package dk.sdu.cbse.common.ui;

import javafx.scene.layout.Background;

/**
 * {@code IBackgroundService} creates and returns a {@link Background} to be used in Core, where it sets the background
 * of the game.
 *
 * @see Background
 */
public interface IBackgroundService {
    /**{@code getBackground} retrieves a {@link Background}.<br></br>
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> No background has been set for the game </li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     * <li> A {@link Background} is created and returned </li>
     *</ul>
     *
     * @return A {@link Background} from JavaFX
     *
     * @see Background
     */
    Background getBackground();
}
