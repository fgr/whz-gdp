package asteroids.model.mshapes;

public class MCircle implements IMShape {

	private final MPoint center;
	private final double radius;

	public MCircle(double radius, MPoint center) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public void move(double deltaX, double deltaY) {
		center.move(deltaX, deltaY);
	}

	@Override
	public void rotate(MPoint center, double degrees) {
		center.rotate(center, degrees);
	}

	public MPoint getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}
}
