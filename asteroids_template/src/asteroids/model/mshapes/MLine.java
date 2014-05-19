package asteroids.model.mshapes;


public class MLine extends MPolygon {
	public MLine(MPoint start, MPoint end) {
		points.add(start);
		points.add(end);
	}
}
