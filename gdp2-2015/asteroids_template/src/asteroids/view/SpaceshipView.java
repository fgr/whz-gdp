package asteroids.view;

import java.util.List;

import javafx.scene.shape.Polygon;

/**
 * A {@link SpaceshipView} instance is responsible for visualising the space
 * ship model element. It is a JavaFX {@link Polygon} whose points can be
 * {@link #updatePoints(List)} to modify the {@link SpaceshipView}'s position
 * (and shape).
 * 
 * @author Frank Grimm
 */
public class SpaceshipView extends Polygon {
	public SpaceshipView(List<Double> points) {
		super();
		updatePoints(points);
	}

	public void updatePoints(List<Double> points) {
		getPoints().setAll(points);
	}
}
