package shapes;

import javafx.scene.paint.Color;
import whiteBoard.WhiteBoard;

public class Circle extends Shape {
	private Point center;
	private final int radius;

	/**
	 * Erstellt einen Kreis mit Radius und Mittelpunkt.
	 */
	public Circle(int radius, Point center, WhiteBoard wb) {
		this(radius, center, null, false, wb);
	}

	/**
	 * Erstellt einen Kreis mit Radius, Mittelpunkt, Farbe und ob er ausgefuellt
	 * ist.
	 */
	public Circle(int radius, Point center, Color color, boolean solid,
			WhiteBoard wb) {
		super(wb);
		this.center = center;
		this.radius = radius;
		setColor(color);
		setSolid(solid);
	}

	/**
	 * Zeichnet den {@link Circle Kreis} auf dem {@link WhiteBoard}.
	 */
	@Override
	public void draw() {
		remove();
		WhiteBoard wb = getWhiteBoard();
		Object repOnWb = wb.drawCircle(center.getX(), center.getY(), radius,
				getColor(), getSolid());
		setRepresentationOnWhiteBoard(repOnWb);
	}

	/**
	 * Bewegt den Kreis und zeichnet ihn an neuer Position neu.
	 */
	@Override
	public void move(double deltaX, double deltaY) {
		center = new Point(center.getX() + deltaX, center.getY() + deltaY);
		draw();
	}

	/**
	 * Rotiert den Kreis und zeichnet ihn neu.
	 */
	@Override
	public void rotate(Point centerRot, double degrees) {
		center.rotate(centerRot, degrees);
		draw();
	}
}