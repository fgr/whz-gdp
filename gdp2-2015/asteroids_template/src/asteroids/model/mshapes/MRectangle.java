package asteroids.model.mshapes;

import java.util.Arrays;
import java.util.List;

/**
 * @author Frank Grimm
 */
public class MRectangle extends MPolygon {
	public MRectangle(MPoint topLeft, double width, double heigth) {
		super(getPoints(topLeft, width, heigth));
	}

	private static List<MPoint> getPoints(MPoint topLeft, double width, double heigth) {
		double tlX = topLeft.getX();
		double tlY = topLeft.getY();

		return Arrays.asList(//
				new MPoint(tlX, tlY),//
				new MPoint(tlX + width, tlY),//
				new MPoint(tlX + width, tlY - heigth),//
				new MPoint(tlX, tlY - heigth));
	}
}
