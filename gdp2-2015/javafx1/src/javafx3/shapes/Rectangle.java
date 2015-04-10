package javafx3.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Rectangle {
	private final DrawingBoard drawingBoard;
	private double x;
	private double y;
	private double width;
	private double height;
	private Paint fillColor;

	Rectangle(double x, double y, double width, double height, DrawingBoard board) {
		this(x, y, width, height, null, board);
	}

	Rectangle(double x, double y, double width, double height, Paint fillColor, DrawingBoard board) {
		drawingBoard = board;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fillColor = fillColor;
	}

	void draw(GraphicsContext gc) {
		gc.setLineWidth(1);
		if (fillColor != null) {
			gc.setFill(fillColor);
			gc.fillRect(x, y, width, height);
		} else {
			gc.setStroke(Color.BLACK);
			gc.strokeRect(x, y, width, height);
		}
	}

	public void move(double deltaX, double deltaY) {
		x += deltaX;
		y += deltaY;
		drawingBoard.refresh();
	}
}
