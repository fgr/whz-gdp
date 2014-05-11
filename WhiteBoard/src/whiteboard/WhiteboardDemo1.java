package whiteBoard;

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
 * 
 */
@ThreadSafe
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@GuardedBy("wb")
	private WhiteBoard wb;

	@GuardedBy("wb")
	private Object testCircle;

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		{
			Scene scene = new Scene(root, 700, 500);
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
												wb.removeShape(testCircle);
											}
										}
									});
						}
					}
				}

				root.getChildren().add(mb);
			}

			{
				wb = new WhiteBoard();
				root.getChildren().add(wb);
			}
		}

		primaryStage.setTitle("WhiteBoard");
		primaryStage.show();

		Thread test = new Thread() {
			@Override
			public void run() {
				int i = 0;
				while (true) {
					synchronized (wb) {
						wb.removeShape(testCircle);
						testCircle = wb.drawCircle(i, i + 10, 25);
					}

					if (i > 750)
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
		test.start();
	}
}
