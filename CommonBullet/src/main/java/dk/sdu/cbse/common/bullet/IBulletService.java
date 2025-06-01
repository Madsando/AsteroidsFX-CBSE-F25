package dk.sdu.cbse.common.bullet;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityComponent;

/**
 * {@code IBulletService} creates Bullets, which are a specific collection of {@link IEntityComponent IEntityComponents}.
 *
 * @see Entity
 * @see IEntityComponent
 */
public interface IBulletService {
    /**{@code createBullet} is used create a bullet when called.<br></br>
     * <b>Pre-conditions</b>
     * <ul>
     *  <li> The interface has been implemented </li>
     *  <li> Note: The implementation may feature a cooldown, which needs to be over for it to spawn a bullet</li>
     * </ul> <br>
     * <b>Post-conditions</b>
     * <ul>
     * <li> Bullet has been created </li>
     *</ul>
     *
     * @param shooter Relevant data about the game for the Plugin to use
     *
     * @return An {@link Entity} with the needed {@link IEntityComponent IEntityComponents} to be a {@code Bullet}.
     *
     * @see Entity
     * @see IEntityComponent
     */
    Entity createBullet(Entity shooter);
}
