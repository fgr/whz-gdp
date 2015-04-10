package asteroids.model;

import asteroids.model.mshapes.MPoint;

abstract class SpriteModel {
	protected double velocity = 0;
	protected double direction = 0;
	protected final MPoint centre;

	public SpriteModel(double x, double y) {
		centre = new MPoint(x, y);
	}

	public final void updatePosition() {
		double rads = Math.toRadians(direction);

		double deltaX = velocity * Math.cos(rads);
		double deltaY = velocity * Math.sin(rads);
		move(deltaX, deltaY);
	}

	protected final void move(double deltaX, double deltaY) {
		doMove(deltaX, deltaY);
	}

	protected void doMove(double deltaX, double deltaY) {
		centre.move(deltaX, deltaY);
	}

	public MPoint getCentre() {
		return new MPoint(centre);
	}

	public double getDirection() {
		return direction;
	}
}
