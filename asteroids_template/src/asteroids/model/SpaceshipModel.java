package asteroids.model;

import java.util.Arrays;
import java.util.List;

import asteroids.model.mshapes.MPoint;
import asteroids.model.mshapes.MPolygon;

public class SpaceshipModel extends SpriteModel {
	private final MPolygon polygon;

	public SpaceshipModel(double x, double y) {
		super(x, y);
		polygon = new MPolygon(Arrays.asList(new MPoint(x - 30, y + 20),
				new MPoint(x - 30, y - 20), new MPoint(x + 30, y)));
	}

	public void accelerate() {
		velocity = (velocity * 1.1) + 1;
	}

	public void decelerate() {
		if (velocity > 0) {
			velocity = (velocity / 1.1) - 1;
			if (velocity < 0)
				velocity = 0;
		}
	}

	public void turnLeft() {
		rotate(-5);
	}

	public void turnRight() {
		rotate(5);
	}

	public List<MPoint> getPoints() {
		return polygon.getPoints();
	}

	private void rotate(double angleDegree) {
		// TODO
	}
}
