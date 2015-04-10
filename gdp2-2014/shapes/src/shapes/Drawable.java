package shapes;

/**
 * @author Frank Grimm
 */
public interface Drawable {
	public void draw();

	public void move(double deltaX, double deltaY);

	/**
	 * Dreht das {@link Polygon}.
	 * 
	 * @param centre
	 *            Rotationszentrum.
	 * @param degrees
	 *            Rotationswinkel in Grad.
	 */
	void rotate(Point centre, double degrees);
}
