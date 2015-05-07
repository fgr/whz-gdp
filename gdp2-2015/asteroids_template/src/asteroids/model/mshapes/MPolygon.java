package asteroids.model.mshapes;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Frank Grimm
 */
public class MPolygon implements IMShape {
	protected final List<MPoint> points;

	public MPolygon(List<? extends MPoint> points) {
		Objects.requireNonNull(points);
		this.points = Collections.unmodifiableList(points);
	}

	public List<MPoint> getPoints() {
		return Collections.unmodifiableList(points);
	}

	@Override
	public void move(double deltaX, double deltaY) {
		points.forEach(point -> point.move(deltaX, deltaY));
	}

	@Override
	public void rotate(MPoint center, double degrees) {
		points.forEach(point -> point.rotate(center, degrees));
	}

	public MPoint getCenter() {
		return centerOfMass();
	}

	/**
	 * Calculate the ploygon's area.
	 * 
	 * @see http://local.wasp.uwa.edu.au/~pbourke/geometry/polyarea/
	 * 
	 * @return polygon's area
	 */
	private double area() {
		int i, j, n = points.size();
		double area = 0;

		for (i = 0; i < n; ++i) {
			j = (i + 1) % n;
			MPoint pi = points.get(i);
			MPoint pj = points.get(j);
			area += pi.getX() * pj.getY();
			area -= pj.getX() * pi.getY();
		}
		area /= 2.0;
		return (area);
	}

	/**
	 * Calculate polygon's center of mass.
	 * 
	 * @see http://local.wasp.uwa.edu.au/~pbourke/geometry/polyarea/
	 * 
	 * @return polygon's center of mass.
	 */
	private MPoint centerOfMass() {
		double cx = 0, cy = 0;
		int i, j, n = points.size();

		double factor = 0;
		for (i = 0; i < n; ++i) {
			j = (i + 1) % n;
			MPoint pi = points.get(i);
			MPoint pj = points.get(j);
			factor = (pi.getX() * pj.getY() - pj.getX() * pi.getY());
			cx += (pi.getX() + pj.getX()) * factor;
			cy += (pi.getY() + pj.getY()) * factor;
		}
		double area = area() * 6.0f;
		factor = 1 / area;
		cx *= factor;
		cy *= factor;
		return new MPoint(cx, cy);
	}
}
