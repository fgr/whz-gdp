package asteroids.model.mshapes;

/**
 * @author Frank Grimm, Mai 2014
 */
public final class MPoint {
	public double x;
	public double y;

	public MPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public MPoint(MPoint p) {
		this(p.x, p.y);
	}

	public void rotate(MPoint center, double degrees) {
		double angleRad = Math.toRadians(degrees);
		double s = Math.sin(angleRad);
		double c = Math.cos(angleRad);

		x -= center.x;
		y -= center.y;

		// rotate point
		double xnew = x * c - y * s;
		double ynew = x * s + y * c;

		x = xnew + center.x;
		y = ynew + center.y;
	}

	public void move(double deltaX, double deltaY) {
		x += deltaX;
		y += deltaY;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
