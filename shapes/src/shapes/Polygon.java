package shapes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.paint.Color;
import whiteBoard.WhiteBoard;

/**
 * @author Frank Grimm
 */
public class Polygon extends Shape {
	protected final ArrayList<Point> points;

	public Polygon(WhiteBoard wb) {
		super(wb);
		points = new ArrayList<Point>();
	}

	/**
	 * Zeichnet das {@link Polygon}.
	 */
	@Override
	public void draw() {
		remove();

		List<Point> ps = getPoints();

		double[] x = new double[ps.size()];
		double[] y = new double[ps.size()];

		for (int i = 0; i < ps.size(); i++) {
			Point p = ps.get(i);
			x[i] = p.getX();
			y[i] = p.getY();
		}

		WhiteBoard wb = getWhiteBoard();
		Object shapeOnWb;
		Color c = getColor();
		if (c == null)
			shapeOnWb = wb.drawPolygon(x, y);
		else
			shapeOnWb = wb.drawPolygon(x, y, c, getSolid(), 0.0);
		setRepresentationOnWhiteBoard(shapeOnWb);
	}

	/**
	 * Liefert die {@link Point Punkte} des {@link Polygon}s zurueck.
	 */
	public List<Point> getPoints() {
		return Collections.unmodifiableList(points);
	}

	/**
	 * Bewegt das {@link Polygon}.
	 */
	@Override
	public void move(double deltaX, double deltaY) {
		for (Point point : getPoints())
			point.move(deltaX, deltaY);
		draw();
	}

	/**
	 * Dreht das {@link Polygon}.
	 * 
	 * @param centre
	 *            Rotationszentrum.
	 * @param degrees
	 *            Rotationswinkel.
	 */
	@Override
	public void rotate(Point centre, double degrees) {
		for (Point point : getPoints())
			point.rotate(centre, degrees);

		draw();
	}
}