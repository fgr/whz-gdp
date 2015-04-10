package javafx2.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle {
	private final GraphicsContext gc;
	private double x;
	private double y;
	private double r;
	private Paint fillColor;

	Circle(double centerX, double centerY, double radius, Paint fill, GraphicsContext gc) {
		this.gc = gc;
		x = centerX;
		y = centerY;
		r = radius;
		fillColor = fill;
	}

	void draw() {
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
		draw();
	}
}
