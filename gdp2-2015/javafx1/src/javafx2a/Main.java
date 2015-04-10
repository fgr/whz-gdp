package javafx2a;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx2a.shapes.Circle;
import javafx2a.shapes.DrawingBoard;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Drawing board");
		DrawingBoard board = new DrawingBoard(primaryStage);
		Circle c = board.drawCircle(50, 100, 25, Color.GREEN);
		c.move(100, 50);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
