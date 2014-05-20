package asteroids.model.mshapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MPolygon implements IMShape {
	protected ArrayList<MPoint> points;

	public MPolygon(Collection<? extends MPoint> points) {
		this.points = new ArrayList<>(points);
	}

	public MPolygon() {
		this(Collections.<MPoint> emptyList());
	}

	public List<MPoint> getPoints() {
		return Collections.unmodifiableList(points);
	}

	@Override
	public void move(double deltaX, double deltaY) {
		for (MPoint point : getPoints())
			point.move(deltaX, deltaY);
	}

	@Override
	public void rotate(MPoint center, double degrees) {
		for (MPoint point : getPoints())
			point.rotate(center, degrees);
	}
}
