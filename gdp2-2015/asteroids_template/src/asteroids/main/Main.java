package asteroids.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import asteroids.ctrl.GameController;
import asteroids.ctrl.ViewPartFactory;
import asteroids.model.PlayingField;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		{
			Scene scene = new Scene(root, 700, 500);
			primaryStage.setScene(scene);

			{
				PlayingField rootModelElement = new PlayingField(700, 500);
				GameController gc = new GameController(rootModelElement, ViewPartFactory.INSTANCE, root);
				gc.activate();
			}
		}

		primaryStage.setTitle("Asteroids");
		primaryStage.show();
	}
}
