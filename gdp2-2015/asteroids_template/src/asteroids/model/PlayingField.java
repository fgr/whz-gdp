package asteroids.model;

/**
 * An instance of this class represents a playing field model element. It
 * contains/references the {@link #spaceship} which is a sub-/child model
 * element.
 * 
 * @author Frank Grimm
 *
 */
public class PlayingField {
	private SpaceshipModel spaceship;
	private final double width;
	private final double height;

	public PlayingField(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public SpaceshipModel getSpaceship() {
		if (spaceship == null)
			spaceship = new SpaceshipModel(0, 0);
		return spaceship;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
