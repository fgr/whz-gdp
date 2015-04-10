package javafx3;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx3.shapes.Circle;
import javafx3.shapes.DrawingBoard;
import javafx3.shapes.Rectangle;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Drawing board");
		DrawingBoard board = new DrawingBoard(primaryStage);
		Circle c = board.drawCircle(25, 50, 15, Color.BLUEVIOLET);
		c.move(100, 50);
		Rectangle r = board.drawRect(25, 50, 15, 35, null);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
