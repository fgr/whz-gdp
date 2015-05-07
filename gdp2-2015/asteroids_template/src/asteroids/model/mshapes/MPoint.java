package asteroids.model.mshapes;

/**
 * @author Frank Grimm
 */
public final class MPoint {
	private double x;
	private double y;

	public MPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public MPoint(MPoint p) {
		this(p.x, p.y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void rotate(MPoint center, double degrees) {
		double rad = Math.toRadians(degrees);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);

		double newX = cos * (x - center.x) - sin * (y - center.y) + center.x;
		double newY = sin * (x - center.x) + cos * (y - center.y) + center.y;

		x = newX;
		y = newY;
	}

	public void move(double deltaX, double deltaY) {
		x += deltaX;
		y += deltaY;
	}

	@Override
	public String toString() {
		return String.format("(%f;%f)", x, y);
	}
}
