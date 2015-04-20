package javafx0;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// muss public sein
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Drawing board");

		Group root = new Group();

		Canvas canvas = new Canvas(300, 250);
		root.getChildren().add(canvas);

		{
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		}

		{
			GraphicsContext gc = canvas.getGraphicsContext2D();
			{
				gc.setStroke(Color.CYAN);
				gc.setLineWidth(5);
				double centerX = 100;
				double centerY = 50;
				double radiusX, radiusY;
				radiusX = radiusY = 30;
				gc.strokeOval(centerX, centerY, radiusX, radiusY);
			}

			{
				gc.setFill(Color.RED);
				gc.fillOval(50, 100, 25, 50);
			}
		}
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
