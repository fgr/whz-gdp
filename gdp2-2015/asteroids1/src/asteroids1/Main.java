package asteroids1;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Frank Grimm
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Asteroids");
		Game game = new Game(primaryStage);
		game.start();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
