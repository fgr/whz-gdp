package shapes;

import javafx.scene.paint.Color;
import whiteBoard.WhiteBoard;

/**
 * Klasse, deren Instanzen {@link Line Linien} repraesentieren.
 */
public class Line extends Polygon {
	public Line(Point start, Point end, WhiteBoard wb) {
		this(start, end, null, wb);
	}

	/**
	 * Erstellt eine {@link Line} mit einer bestimmten {@link Color Farbe}.
	 */
	public Line(Point start, Point end, Color color, WhiteBoard wb) {
		super(wb);
		points.add(start);
		points.add(end);
		setColor(color);
	}
}
