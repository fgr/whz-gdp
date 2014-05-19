package asteroids.model.mshapes;

public interface IMShape {
	void rotate(MPoint center, double degrees);

	void move(double deltaX, double deltaY);
}
