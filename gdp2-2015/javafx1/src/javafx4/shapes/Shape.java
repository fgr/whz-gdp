package javafx4.shapes;

import java.util.Objects;

import javafx.scene.canvas.GraphicsContext;

abstract class Shape {
	private final DrawingBoard drawingBoard;

	public Shape(DrawingBoard board) {
		Objects.requireNonNull(board);
		drawingBoard = board;
	}

	protected DrawingBoard getDrawingBoard() {
		return drawingBoard;
	}

	protected abstract void draw(GraphicsContext gc);
}
