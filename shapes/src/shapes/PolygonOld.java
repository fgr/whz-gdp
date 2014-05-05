package shapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import whiteBoard.WhiteBoard;

public class PolygonOld {
	public static PolygonOld createFromXsAndYs(WhiteBoard whiteBoard, double[] xs,
			double[] ys) {
		if (xs.length != ys.length)
			throw new IllegalArgumentException(
					"Xs and Ys have to have the same dimension!");

		List<PointOld> points = new ArrayList<>();
		for (int i = 0; i < xs.length; ++i) {
			points.add(new PointOld(xs[i], ys[i]));
		}
		return new PolygonOld(whiteBoard, points);
	}

	private final List<PointOld> points;
	private final WhiteBoard whiteBoard;
	/** Representation of this polygon when it is drawn on the given whiteboard */
	private Object whiteBoardShape;

	public PolygonOld(WhiteBoard wb, PointOld... points) {
		this(wb, Arrays.asList(points));
	}

	public PolygonOld(WhiteBoard wb, Collection<? extends PointOld> points) {
		this.whiteBoard = wb;
		this.points = new ArrayList<>(points);
	}

	public void setPoints(Collection<? extends PointOld> points) {
		this.points.clear();
		this.points.addAll(points);
		draw();
	}

	public void move(double deltaX, double deltaY) {
		for (PointOld p : points)
			p.move(deltaX, deltaY);
		draw();
	}

	public void rotate(PointOld center, double angleDegree) {
		for (PointOld p : points)
			p.rotate(center, angleDegree);
		draw();
	}

	public void draw() {
		if (whiteBoardShape != null)
			whiteBoard.removeShape(whiteBoardShape);
		whiteBoardShape = whiteBoard.drawPolygon(getXs(), getYs());
	}

	private double[] getXs() {
		int sz = points.size();
		double[] xs = new double[sz];
		for (int i = 0; i < sz; ++i)
			xs[i] = points.get(i).x;
		return xs;
	}

	private double[] getYs() {
		int sz = points.size();
		double[] ys = new double[sz];
		for (int i = 0; i < sz; ++i)
			ys[i] = points.get(i).y;
		return ys;
	}
}
