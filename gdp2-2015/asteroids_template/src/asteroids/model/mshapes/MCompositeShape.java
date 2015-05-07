package asteroids.model.mshapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Instances of this {@link MCompositeShape class} represent {@link IMShape}s
 * which can contain other {@link IMShape}s.
 * 
 * @author Frank Grimm
 *
 */
public class MCompositeShape implements IMShape {
	private final List<IMShape> shapes;

	public MCompositeShape(IMShape... s) {
		shapes = new ArrayList<>();
		if (s != null)
			shapes.addAll(Arrays.asList(s));
	}

	@Override
	public void rotate(MPoint center, double degrees) {
		shapes.forEach(s -> s.rotate(center, degrees));
	}

	@Override
	public void move(double deltaX, double deltaY) {
		shapes.forEach(s -> s.move(deltaX, deltaY));
	}
}
