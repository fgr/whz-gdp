package shapes;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author Frank Grimm
 */
public class Polygon {
	private final List<Point> points;
	private final Paint fillColor;

	public Polygon(List<Point> points, Paint fill) {
		Objects.requireNonNull(points);
		if (points.size() < 3) {
			throw new IllegalArgumentException("A ploygon must consist of at least 3 points.");
		}

		fillColor = fill;

		this.points = Collections.unmodifiableList(points);
	}

	public void draw(GraphicsContext gc) {
		gc.setLineWidth(1);
		if (fillColor != null) {
			gc.setFill(fillColor);
			gc.fillPolygon(getXCoords(points), getYCoords(points), points.size());
		} else {
			gc.setStroke(Color.BLACK);
			gc.strokePolygon(getXCoords(points), getYCoords(points), points.size());
		}
	}

	public void move(double deltaX, double deltaY) {
		points.forEach(point -> point.move(deltaX, deltaY));
	}

	public void rotate(Point center, double degrees) {
		points.forEach(point -> point.rotate(center, degrees));
	}

	private static double[] getXCoords(List<Point> points) {
		double[] xCoords = new double[points.size()];

		for (int i = 0; i < points.size(); ++i) {
			Point p = points.get(i);
			xCoords[i] = p.getX();
		}

		return xCoords;
	}

	private static double[] getYCoords(List<Point> points) {
		double[] yCoords = new double[points.size()];

		for (int i = 0; i < points.size(); ++i) {
			Point p = points.get(i);
			yCoords[i] = p.getY();
		}

		return yCoords;
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
			Point pi = points.get(i);
			Point pj = points.get(j);
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
	public Point centerOfMass() {
		double cx = 0, cy = 0;
		int i, j, n = points.size();

		double factor = 0;
		for (i = 0; i < n; ++i) {
			j = (i + 1) % n;
			Point pi = points.get(i);
			Point pj = points.get(j);
			factor = (pi.getX() * pj.getY() - pj.getX() * pi.getY());
			cx += (pi.getX() + pj.getX()) * factor;
			cy += (pi.getY() + pj.getY()) * factor;
		}
		double area = area() * 6.0f;
		factor = 1 / area;
		cx *= factor;
		cy *= factor;
		return new Point(cx, cy);
	}
}
