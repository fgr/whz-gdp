package asteroids.model.mshapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MCompositeShape implements IMShape {
	private final List<IMShape> shapes;

	public MCompositeShape(IMShape... s) {
		shapes = new ArrayList<>();
		if (s != null)
			shapes.addAll(Arrays.asList(s));
	}

	@Override
	public void rotate(MPoint center, double degrees) {
		for (IMShape s : shapes)
			s.rotate(center, degrees);
	}

	@Override
	public void move(double deltaX, double deltaY) {
		for (IMShape s : shapes)
			s.move(deltaX, deltaY);
	}
}
