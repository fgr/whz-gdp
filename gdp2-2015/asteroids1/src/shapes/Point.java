package shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Frank Grimm
 */
public class Point {
	public static List<Point> asPointsList(int... coordPairs) {
		Objects.requireNonNull(coordPairs);
		if ((coordPairs.length % 2) != 0) {
			throw new IllegalArgumentException("Pairs of X,Y-coordinates expected.");
		}

		ArrayList<Point> points = new ArrayList<>(coordPairs.length);
		for (int i = 0; i < coordPairs.length; i += 2) {
			int x = coordPairs[i];
			int y = coordPairs[i + 1];
			Point p = new Point(x, y);
			points.add(p);
		}

		return points;
	}

	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
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

	public void rotate(Point center, double degrees) {
		double rad = Math.toRadians(degrees);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);

		// 1. translate to origin (center moves to origin, x moves relative to center)
		double xnew = x - center.x;
		double ynew = y - center.y;

		// 2. rotate
		xnew = (xnew * cos) - (ynew * sin);
		ynew = (ynew * cos) + (xnew * sin);

		// 3. translate back to center
		xnew += center.x;
		ynew += center.y;

		// 4. update point's coordinates
		x = xnew;
		y = ynew;
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
