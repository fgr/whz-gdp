package asteroids.model.mshapes;

/**
 * @author Frank Grimm, Mai 2014
 */
public interface IMShape {
	void rotate(MPoint center, double degrees);

	void move(double deltaX, double deltaY);
}
