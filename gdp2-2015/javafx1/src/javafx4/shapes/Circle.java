package javafx4.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle extends Shape {
	private double x;
	private double y;
	private double r;
	private Paint fillColor;

	Circle(double centerX, double centerY, double radius, DrawingBoard board) {
		this(centerX, centerY, radius, null, board);
	}

	Circle(double centerX, double centerY, double radius, Paint fill, DrawingBoard board) {
		super(board);
		x = centerX;
		y = centerY;
		r = radius;
		fillColor = fill;
	}

	@Override
	protected void draw(GraphicsContext gc) {
		gc.setLineWidth(1);
		if (fillColor != null) {
			gc.setFill(fillColor);
			gc.fillOval(x, y, r, r);
		} else {
			gc.setStroke(Color.BLACK);
			gc.strokeOval(x, y, r, r);
		}
	}

	public void move(double deltaX, double deltaY) {
		x += deltaX;
		y += deltaY;
		getDrawingBoard().refresh();
	}
}
