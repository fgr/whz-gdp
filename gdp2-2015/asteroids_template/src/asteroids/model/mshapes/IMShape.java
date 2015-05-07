package asteroids.model.mshapes;

/**
 * Instances implementing this {@link IMShape interface} representing geometric
 * shapes in the mathematical sense. That is, these shapes do not know how to
 * display themselves on a screen, but are mathematical representation of
 * {@link asteroids.model.mshapes.MPoint points},
 * {@link asteroids.model.mshapes.MLine lines} and so on which can be
 * {@link asteroids.model.mshapes.IMShape#move(double, double) moved} and
 * {@link asteroids.model.mshapes.IMShape#rotate(MPoint, double) rotated}.
 * 
 * 
 * @author Frank Grimm
 *
 */
public interface IMShape {
	void rotate(MPoint center, double degrees);

	void move(double deltaX, double deltaY);
}
