package whiteBoard;

import java.util.concurrent.ExecutionException;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Frank Grimm
 * 
 */
@ThreadSafe
public class WhiteBoard extends Group {
	public WhiteBoard() {
	}

	@GuardedBy("this")
	private synchronized void addNode(final Node node) {
		if (Platform.isFxApplicationThread()) {
			if (!getChildren().add(node))
				throw new RuntimeException("Could not add node.");
		} else {
			try {
				JavafxPlatformUtils.runAndWait(new Runnable() {
					@Override
					public void run() {
						getChildren().add(node);
					}
				});
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException("Could not add node.", e);
			}
		}
	}

	@GuardedBy("this")
	private synchronized void removeNode(final Node node) {
		if (Platform.isFxApplicationThread()) {
			if (!getChildren().remove(node))
				throw new RuntimeException("Could not remove node.");
		} else {
			try {
				JavafxPlatformUtils.runAndWait(new Runnable() {
					@Override
					public void run() {
						if (!getChildren().remove(node))
							throw new RuntimeException("Could not remove node.");
					}
				});
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException("Could not remove node.", e);
			}
		}
	}

	/**
	 * Zeichnet eine Linie in {@link Color.BLACK schwarzer Farbe}.
	 * 
	 * @param xfrom
	 *            x Startkoordinate
	 * @param yfrom
	 *            y Startkoordinate
	 * @param xto
	 *            x Zielkoordinate
	 * @param yto
	 *            y Zielkoordinate
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawLine(double xfrom, double yfrom, double xto, double yto) {
		return drawLine(xfrom, yfrom, xto, yto, Color.BLACK);
	}

	/**
	 * Zeichnet eine farbige Linie.
	 * 
	 * @param xfrom
	 *            x Startkoordinate
	 * @param yfrom
	 *            y Startkoordinate
	 * @param xto
	 *            x Zielkoordinate
	 * @param yto
	 *            y Zielkoordinate
	 * @param color
	 *            {@link Color Linienfarbe}
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawLine(double xfrom, double yfrom, double xto, double yto,
			Color color) {
		Line s = new Line(xfrom, yfrom, xto, yto);
		s.setStroke(color);

		addNode(s);

		return s;
	}

	/**
	 * Zeichnet einen parabolischen Bogen in default-Farbe.
	 * 
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 * @param xfrom
	 *            x Startkoordinate
	 * @param yfrom
	 *            y Startkoordinate
	 * @param xto
	 *            x Zielkoordinate
	 * @param yto
	 *            y Zielkoordinate
	 * @param excentricity
	 *            Maximale Auslenkung des Bogens aus der Verbindungsgerade
	 */
	public Object drawArc(double xfrom, double yfrom, double xto, double yto,
			double excentricity) {
		return drawArc(xfrom, yfrom, xto, yto, excentricity, Color.BLACK, false);
	}

	/**
	 * Zeichnet einen farbigen, evtl. gefuellten parabolischen Bogen.
	 * 
	 * @param xfrom
	 *            x Startkoordinate
	 * @param yfrom
	 *            y Startkoordinate
	 * @param xto
	 *            x Zielkoordinate
	 * @param yto
	 *            y Zielkoordinate
	 * @param excentricity
	 *            Maximale Auslenkung des Bogens aus der Verbindungsgerade.
	 * @param color
	 *            {@link Color Linienfarbe}
	 * @param solid
	 *            true, wenn Zeichnungsobjekt gefuellt werden soll.
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawArc(double xfrom, double yfrom, double xto, double yto,
			double excentricity, Color color, boolean solid) {
		double xs, ys, d, dx, dy, r, exx, exy;

		dx = xto - xfrom;
		dy = yto - yfrom;
		d = Math.sqrt(dx * dx + dy * dy);
		r = excentricity;
		exx = dy * r / d;
		exy = dx * r / d;
		xs = ((xfrom + xto) / 2) - 2 * exx;
		ys = ((yfrom + yto) / 2) + 2 * exy;

		QuadCurve s = new QuadCurve(xfrom, yfrom, xs, ys, xto, yto);

		if (solid) {
			s.setFill(color);
		} else {
			s.setFill(Color.TRANSPARENT);
			s.setStroke(color);
		}

		addNode(s);

		return s;
	}

	/**
	 * Zeichnet einen {@link Color.BLACK schwarzen} Punkt.
	 * 
	 * @param x
	 *            Koordinate
	 * @param y
	 *            Koordinate
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawPoint(double x, double y) {
		return drawPoint(x, y, Color.BLACK);
	}

	/**
	 * Zeichnet einen in {@link Color farbigen} Punkt.
	 * 
	 * @param x
	 *            Koordinate
	 * @param y
	 *            Koordinate
	 * @param color
	 *            Linienfarbe. {@link java.awt.Color}
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawPoint(double x, double y, Color color) {
		Ellipse s = new Ellipse(x - 1, y - 1, 2, 2);
		s.setFill(color);

		addNode(s);

		return s;
	}

	/**
	 * Zeichnet eine {@link Color.BLACK schwarze} Ellipse.
	 * 
	 * @param x
	 *            x-Wert des Mittelpunkts.
	 * @param y
	 *            y-Wert des Mittelpunkts.
	 * @param hx
	 *            x-Ausdehnung.
	 * @param hy
	 *            y-Ausdehnung.
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawEllipse(double x, double y, double hx, double hy) {
		return drawEllipse(x, y, hx, hy, Color.BLACK, false, 0.0);
	}

	/**
	 * Zeichnet eine {@link Color farbige} Ellipse.
	 * 
	 * @param x
	 *            x-Wert des Mittelpunkts.
	 * @param y
	 *            y-Wert des Mittelpunkts.
	 * @param hx
	 *            x-Ausdehnung.
	 * @param hy
	 *            y-Ausdehnung.
	 * @param color
	 *            in {@link Color Fuellfarbe}.
	 * @param solid
	 *            Gibt an, ob Zeichnungsobjekt gefuellt werden soll.
	 * @param rotation
	 *            Drehung der Ellipse (<em>Winkel im Bogenmass!</em>).
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawEllipse(double x, double y, double hx, double hy,
			Color color, boolean solid, double rotation) {
		Ellipse s = new Ellipse(x - hx, y - hy, 2 * hx, 2 * hy);

		if (solid) {
			s.setFill(color);
		} else {
			s.setFill(Color.TRANSPARENT);
			s.setStroke(color);
		}

		addNode(s);

		return s;
	}

	/**
	 * Zeichnet einem {@link Color.BLACK schwarzen} Kreis.
	 * 
	 * @param x
	 *            x-Wert des Mittelpunkts.
	 * @param y
	 *            y-Wert des Mittelpunkts.
	 * @param radius
	 *            Ausdehnung.
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawCircle(double x, double y, double radius) {
		return drawCircle(x, y, radius, Color.BLACK, false);
	}

	/**
	 * Zeichnet einem {@link Color farbigen} Kreis.
	 * 
	 * @param x
	 *            x-Wert des Mittelpunkts.
	 * @param y
	 *            y-Wert des Mittelpunkts.
	 * @param radius
	 *            Ausdehnung.
	 * @param color
	 *            {@link Color Kreisfarbe}.
	 * @param solid
	 *            Gibt an, ob Zeichnungsobjekt gefuellt werden soll.
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */

	public Object drawCircle(double x, double y, double radius, Color color,
			boolean solid) {

		Circle s = new Circle(x, y, radius);

		if (solid && (color != null)) {
			s.setFill(color);
		} else {
			s.setFill(Color.TRANSPARENT);
			s.setStroke(color);
		}

		addNode(s);

		return s;
	}

	/**
	 * Zeichnet ein {@link Color.BLACK schwarzes} Polygon.
	 * 
	 * @param x
	 *            x-Wert des Eckpunkts.
	 * @param y
	 *            y-Wert des Eckpunkts.
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawPolygon(double[] x, double[] y) {
		return drawPolygon(x, y, Color.BLACK, false, 0.0);
	}

	/**
	 * Zeichnet ein {@link Color farbiges} Polygon.
	 * 
	 * @param x
	 *            Eckpunkt-Koordinaten x
	 * @param y
	 *            Eckpunkt-Koordinaten y
	 * @param color
	 *            {@link Color Zeichenarbe}.
	 * @param solid
	 *            Gibt an, ob Zeichnungsobjekt gefuellt werden soll.
	 * @param rotation
	 *            Drehung des Objekts. <em>Winkel im Bogenmass!</em>
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawPolygon(double[] x, double y[], Color color,
			boolean solid, double rotation) {

		int pl = Math.min(x.length, y.length);
		double[] points = new double[pl * 2];

		for (int i = 0; i < pl; i++) {
			points[i * 2] = x[i];
			points[i * 2 + 1] = y[i];
		}

		Polygon s = new Polygon(points);

		if (solid) {
			s.setFill(color);
		} else {
			s.setFill(Color.TRANSPARENT);
			s.setStroke(color);
		}

		addNode(s);

		return s;
	}

	/**
	 * Zeichnet ein {@link Color.BLACK} Rechteck.
	 * 
	 * @param x
	 *            x-Wert des Mittelpunkts.
	 * @param y
	 *            y-Wert des Mittelpunkts.
	 * @param hx
	 *            Breite.
	 * @param hy
	 *            Hoehe.
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawRectangle(double x, double y, double hx, double hy) {
		return drawRectangle(x, y, hx, hy, Color.BLACK, false, 0.0);
	}

	/**
	 * Zeichnet ein {@link Color farbiges} Rechteck.
	 * 
	 * @param x
	 *            x-Wert des Mittelpunkts.
	 * @param y
	 *            y-Wert des Mittelpunkts.
	 * @param hx
	 *            Breite.
	 * @param hy
	 *            Hoehe.
	 * @param color
	 *            {@link Color Farbe} des Rechtecks.
	 * @param solid
	 *            true, wenn Zeichnungsobjekt gefuellt werden soll.
	 * @param rotation
	 *            Drehung des Objekts, Winkel im Bogenmass!
	 * @return Referenz auf das intern verwendete Zeichnungsobjekt. Wird
	 *         benoetigt, um das Objekt wieder zu loeschen.
	 */
	public Object drawRectangle(double x, double y, double hx, double hy,
			Color color, boolean solid, double rotation) {

		Rectangle s = new Rectangle(x - hx, y - hy, 2 * hx, 2 * hy);

		if (solid) {
			s.setFill(color);
		} else {
			s.setFill(Color.TRANSPARENT);
			s.setStroke(color);
		}

		addNode(s);

		return s;
	}

	/**
	 * Loescht ein Zeichenobjekt von der Zeichenfläche.
	 * <p>
	 * Dazu muss das von den Zeichenmethoden zurueckgelieferte Objekt als
	 * Argument an diese {@link WhiteBoard#removeShape(Object) Methode hier}
	 * uebergeben werden.
	 * 
	 * @param o
	 *            Das interne Zeichenobjekt, das von den Zeichenmethoden
	 *            zurueckgeliefert wurde.
	 */
	public synchronized void removeShape(final Object o) {
		if (!getChildren().contains(o))
			throw new IllegalArgumentException("Could not remove Shape " + o
					+ "; it is not shown on this WhiteBoard!");
		removeNode((Node) o);
	}
}
