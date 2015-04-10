package javafx2.shapes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx2.shapes.Circle;

public class DrawingBoard {
	private Canvas canvas;

	public DrawingBoard(Stage parent) {
		Group root = new Group();
		canvas = new Canvas(300, 250);
		root.getChildren().add(canvas);
		parent.setScene(new Scene(root));
	}

	public Circle drawCircle(double centerX, double centerY, double radius, Paint fill) {
		Circle c = new javafx2.shapes.Circle(centerX, centerY, radius, fill, canvas.getGraphicsContext2D());
		c.draw();
		return c;
	}
}
