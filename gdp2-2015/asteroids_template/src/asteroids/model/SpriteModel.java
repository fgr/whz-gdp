package asteroids.model;

/**
 * This class may be used as a base class of concrete model element classes. It
 * implements/defines methods required by all model elements.
 * 
 * @author Frank Grimm
 *
 */
abstract class SpriteModel {
	protected double velocity = 0;
	protected double direction = 0;

	public SpriteModel(double direction, double velocity) {
		this.direction = direction;
		this.velocity = velocity;
	}

	public final void updateForNextFrame() {
		double rad = Math.toRadians(direction);
		double deltaX = Math.sin(rad) * velocity;
		double deltaY = Math.cos(rad) * velocity;
		move(deltaX, deltaY);
	}

	abstract protected void move(double deltaX, double deltaY);
}
