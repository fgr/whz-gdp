package asteroids.model.mshapes;

public class MRectangle extends MPolygon {
	public MRectangle(double topLeftX, double topLeftY, double lx, double ly) {
	}

	public MRectangle(MPoint topLeft, double lx, double ly) {
		double tlX = topLeft.getX();
		double tlY = topLeft.getY();

		points.add(new MPoint(tlX, tlY));
		points.add(new MPoint(tlX + lx, tlY));
		points.add(new MPoint(tlX + lx, tlY - ly));
		points.add(new MPoint(tlX, tlY - ly));
	}
}
