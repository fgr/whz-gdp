package asteroids.model;

import java.util.Arrays;
import java.util.List;

import asteroids.model.mshapes.MPoint;
import asteroids.model.mshapes.MPolygon;

public final class SpaceshipModel extends SpriteModel {
	private final MPolygon polygon;

	public SpaceshipModel(double x, double y) {
		super(90, 0);
		polygon = new MPolygon(Arrays.asList(//
				new MPoint(x - 30, y + 20), //
				new MPoint(x - 30, y - 20), //
				new MPoint(x + 30, y)));
	}

	public void turnLeft() {
		rotate(-5);
	}

	public void turnRight() {
		rotate(5);
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

	public List<MPoint> getPoints() {
		return polygon.getPoints();
	}

	@Override
	protected void move(double deltaX, double deltaY) {
		polygon.move(deltaX, deltaY);
	}

	private void rotate(double angleDegree) {
		direction -= angleDegree;
		polygon.rotate(polygon.getCenter(), angleDegree);
	}
}