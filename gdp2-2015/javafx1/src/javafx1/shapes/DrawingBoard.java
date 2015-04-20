package javafx1.shapes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawingBoard {
	private Canvas canvas;

	public DrawingBoard(Stage parent) {
		Group root = new Group();
		canvas = new Canvas(300, 250);
		root.getChildren().add(canvas);
		parent.setScene(new Scene(root));
	}

	public void drawCircle(double centerX, double centerY, double radius) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5);
		gc.strokeOval(centerX, centerY, radius, radius);
	}
}
