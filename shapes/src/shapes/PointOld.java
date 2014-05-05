package shapes;

public final class PointOld {
	public double x;
	public double y;

	public PointOld(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public PointOld(PointOld p) {
		this(p.x, p.y);
	}

	public void rotate(PointOld centre, double degrees) {
		double angleRad = Math.toRadians(degrees);
		double s = Math.sin(angleRad);
		double c = Math.cos(angleRad);

		x -= centre.x;
		y -= centre.y;

		// rotate point
		double xnew = x * c - y * s;
		double ynew = x * s + y * c;

		x = xnew + centre.x;
		y = ynew + centre.y;
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
