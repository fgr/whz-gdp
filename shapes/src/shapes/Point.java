package shapes;

/**
 * Klasse, deren Instanzen Punkte im x/y-Koordinatensystem repraesentieren.
 * 
 * @author Frank Grimm
 */
public class Point extends Object {
	private double x;
	private double y;

	/**
	 * Erstellt einen {@link Point Punkt}.
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Liefert den x-Wert des {@link Point Punkts}.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gibt den y-Wert wieder.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Bewegt den {@link Point Punkt}.
	 */
	public void move(double deltaX, double deltaY) {
		x = x + deltaX;
		y = y + deltaY;
	}

	/**
	 * Rotiert {@link Point Punkt} um Drehpunkt und Winkel.
	 * 
	 * @param centre
	 *            Rotationszentrum.
	 * @param degrees
	 *            Rotationswinkel <em>in Grad</em>.
	 */
	public void rotate(Point centre, double degrees) {
		double cx = x - centre.getX();
		double cy = y - centre.getY();

		double rads = Math.toRadians(degrees);
		double newX = cx * Math.cos(rads) + cy * Math.sin(rads);
		double newY = -cx * Math.sin(rads) + cy * Math.cos(rads);

		int xx = (int) Math.round(newX);
		int yy = (int) Math.round(newY);
		x = xx + centre.getX();
		y = yy + centre.getY();
	}
}
