package whiteboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Frank Grimm
 * 
 */
public class WhiteboardDemo1 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Whiteboard wb = new Whiteboard();

		wb.drawCircle(100, 50, 30);

		Scene scene = new Scene(wb, 700, 500);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
}
