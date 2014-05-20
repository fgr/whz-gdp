package asteroids.ctrl;

import net.jcip.annotations.ThreadSafe;
import asteroids.model.PlayingField;
import asteroids.model.SpaceshipModel;

@ThreadSafe
public enum ViewPartFactory {
	INSTANCE;

	ViewPart createViewPart(Object modelElement) {
		if (modelElement instanceof PlayingField) {
			return new PlayingFieldViewPart((PlayingField) modelElement);
		} else if (modelElement instanceof SpaceshipModel) {
			SpaceshipModel spaceship = (SpaceshipModel) modelElement;
			return new SpaceshipViewPart(spaceship);
		} else {
			throw new IllegalArgumentException(
					"Don't know how to create ViewPart for model element: "
							+ modelElement);
		}
	}
}
