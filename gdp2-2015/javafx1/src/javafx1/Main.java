package javafx1;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx1.shapes.DrawingBoard;

// muss public sein
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Drawing board");
		DrawingBoard board = new DrawingBoard(primaryStage);
		board.drawCircle(100, 50, 25);
		board.drawCircle(10, 50, 205);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
