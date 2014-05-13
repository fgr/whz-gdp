package asteroids.model.mshapes;

/**
 * @author Frank Grimm, Mai 2014
 */
public final class MLine extends MPolygon {
	public MLine(MPoint start, MPoint end) {
		points.add(start);
		points.add(end);
	}
}
