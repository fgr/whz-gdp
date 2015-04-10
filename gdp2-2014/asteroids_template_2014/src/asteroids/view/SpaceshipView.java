package asteroids.view;

import java.util.List;

import javafx.scene.shape.Polygon;
import asteroids.model.mshapes.MPoint;

public class SpaceshipView extends Polygon {
	public SpaceshipView(MPoint centre, List<Double> points) {
		this(centre.getX(), centre.getY(), points);
	}

	public SpaceshipView(double x, double y, List<Double> points) {
		super();
		getPoints().setAll(points);
	}
}
