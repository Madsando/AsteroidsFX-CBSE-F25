package dk.sdu.cbse.common.services;
import dk.sdu.cbse.common.data.Node;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

/**
 * {@code IEntityComponent} is a marker interface to indicate if a class describes data about an {@link Entity}
 * and to allow polymorphic handling. <br></br>
 * {@code IEntityComponents} are strictly data-only and only have getters/setters. It describes
 * relevant data about an {@link Entity}. Collections of {@code IEntityComponents}
 * are bundled in a {@link Node} by {@link World} for processing by {@link ISystemService}
 *
 * @see Entity
 * @see Node
 * @see World
 * @see ISystemService
 */
public interface IEntityComponent {
}
