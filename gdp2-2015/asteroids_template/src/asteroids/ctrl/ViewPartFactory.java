package asteroids.ctrl;

import net.jcip.annotations.ThreadSafe;
import asteroids.model.PlayingField;
import asteroids.model.SpaceshipModel;

/**
 * This class ({@link ViewPartFactory}) is responsible for
 * {@link ViewPartFactory#createViewPart(Object) creating} the {@link IViewPart}
 * corresponding to a given model element.
 * 
 * <em>The {@link ViewPartFactory#createViewPart(Object) method} has to be
 * adjusted to the actual model element types for which IViewPart shall be created.</em>
 * 
 * @author Frank Grimm
 */
@ThreadSafe
public enum ViewPartFactory {
	INSTANCE;

	IViewPart createViewPart(Object modelElement) {
		if (modelElement instanceof PlayingField) {
			return new PlayingFieldViewPart((PlayingField) modelElement);
		} else if (modelElement instanceof SpaceshipModel) {
			SpaceshipModel spaceship = (SpaceshipModel) modelElement;
			return new SpaceshipViewPart(spaceship);
		} else {
			throw new IllegalArgumentException("Don't know how to create ViewPart for model element: " + modelElement);
		}
	}
}
