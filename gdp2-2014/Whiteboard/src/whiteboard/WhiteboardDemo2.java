package whiteboard;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Frank Grimm
 */
@ThreadSafe
public class WhiteboardDemo2 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@GuardedBy("wb")
	private Whiteboard wb;

	@GuardedBy("wb")
	private Object circle;

	private static final int X_MAX = 700;
	private static final int Y_MAX = 700;

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		{
			Scene scene = new Scene(root, X_MAX, Y_MAX);
			primaryStage.setScene(scene);

			{
				MenuBar mb = new MenuBar();

				{
					Menu testMenu = new Menu("Test");
					mb.getMenus().add(testMenu);

					{
						MenuItem mi = new MenuItem("Remove circle");
						testMenu.getItems().add(mi);

						{
							mi.onActionProperty().set(
									new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent arg0) {
											synchronized (wb) {
												wb.removeShape(circle);
											}
										}
									});
						}
					}
				}

				root.getChildren().add(mb);
			}

			{
				wb = new Whiteboard();
				root.getChildren().add(wb);
				wb.setStyle("-fx-background-color: yellow;");
				wb.setPrefWidth(X_MAX);
				wb.setPrefHeight(Y_MAX);
			}
		}

		primaryStage.setTitle("Whiteboard");
		primaryStage.show();

		Thread updater = new Thread() {
			@Override
			public void run() {
				int i = 40;
				while (true) {
					synchronized (wb) {
						if (circle != null)
							wb.removeShape(circle);
						circle = wb.drawCircle(i, i + 10, 25);
					}

					if (i > X_MAX)
						i = 0;
					else
						++i;
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		updater.start();
	}
}
