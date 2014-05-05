package shapes.demo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shapes.Figure;
import whiteBoard.WhiteBoard;

public class ShapeDemo extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		{
			Scene scene = new Scene(root, 700, 500);
			primaryStage.setScene(scene);

			{
				WhiteBoard wb = new WhiteBoard();
				root.getChildren().add(wb);

				{
					final Figure sm = Figure.createSnowman(wb);
					sm.draw();
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							for (int i = 0; i < 100; ++i) {
								sm.move(i, 0);
								try {
									Thread.sleep(200);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					});
					t.start();
				}
			}
		}

		primaryStage.setTitle("Shapes-Demo");
		primaryStage.show();
	}

}
