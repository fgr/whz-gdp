package javafx2a.shapes;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class DrawingBoard {
	private Canvas canvas;
	private Set<Circle> circles = new HashSet<>();

	public DrawingBoard(Stage parent) {
		Group root = new Group();
		canvas = new Canvas(300, 250);
		root.getChildren().add(canvas);
		parent.setScene(new Scene(root));
	}
	
	public Circle drawCircle(double centerX, double centerY, double radius, DrawingBoard board) {
		return drawCircle(centerX, centerY, radius, Color.BLACK);
	}
	
	public Circle drawCircle(double centerX, double centerY, double radius, Paint fill) {
		Circle c = new Circle(centerX, centerY, radius, fill, this);
		circles.add(c);
		refresh();
		return c;
	}

	void refresh() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Circle c : circles) {
			c.draw(gc);
		}
	}
}
