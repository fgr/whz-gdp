package javafx2;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx2.shapes.Circle;
import javafx2.shapes.DrawingBoard;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Drawing board");
		DrawingBoard board = new DrawingBoard(primaryStage);
		Circle c1 = board.drawCircle(200, 100, 25, Color.BLUE);
		// c1.move(100, 50);
		Circle c2 = board.drawCircle(50, 150, 50, null);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
