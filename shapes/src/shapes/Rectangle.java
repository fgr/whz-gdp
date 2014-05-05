package shapes;

import javafx.scene.paint.Color;
import whiteBoard.WhiteBoard;

/**
 * @author Frank Grimm
 */
public class Rectangle extends Polygon {
	/**
	 * Erstellt ein {@link Rectangle Rechteck}.
	 */
	public Rectangle(Point topLeft, int lx, int ly, WhiteBoard wb) {
		this(topLeft, lx, ly, null, false, wb);
	}

	/**
	 * Erstellt ein {@link Rectangle Rechteck} mit Color- und Solid-Eigenschaft.
	 */
	public Rectangle(Point topLeft, int lx, int ly, Color color, boolean solid,
			WhiteBoard wb) {
		super(wb);

		double tlX = topLeft.getX();
		double tlY = topLeft.getY();

		points.add(new Point(tlX, tlY));
		points.add(new Point(tlX + lx, tlY));
		points.add(new Point(tlX + lx, tlY - ly));
		points.add(new Point(tlX, tlY - ly));

		setColor(color);
		setSolid(solid);
	}
}
