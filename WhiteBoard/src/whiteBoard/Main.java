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

public class Main extends Application {
	// private static boolean isLaunched = false;
	// private final static Object SYNCLAUNCH = new Object();

	private WhiteBoard wb;

	private Object testCircle;

	@Override
	public void start(Stage primaryStage) {
		//
		// synchronized (SYNCLAUNCH) {
		// SYNCLAUNCH.notifyAll();
		// }

		// GridPane rootPane;
		// {
		// rootPane = new GridPane();
		// rootPane.getColumnConstraints().addAll(
		// new ColumnConstraints(),
		// ColumnConstraintsBuilder.create().hgrow(Priority.ALWAYS)
		// .build());
		// rootPane.getRowConstraints().add(
		// RowConstraintsBuilder.create().vgrow(Priority.ALWAYS)
		// .build());
		//
		// {
		// Pane menuePane = new Pane();
		// wb = new WhiteBoard();
		// rootPane.addRow(0, menuePane, wb);
		// }
		// }

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
											wb.removeShape(testCircle);
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

				{
					// Test
					testCircle = wb.drawCircle(100, 100, 50);
				}
			}
		}

		primaryStage.setTitle("WhiteBoard");
		primaryStage.show();

		Thread test = new Thread() {
			@Override
			public void run() {
				int i = 0;
				while (true) {
					wb.removeShape(testCircle);
					testCircle = wb.drawCircle(i, i + 10, 25);

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

	// static void startWb(final WhiteBoard whiteBoard) {
	// initialise();
	//
	// Platform.runLater(new Runnable() {
	// @Override
	// public void run() {
	// whiteBoard.start(new Stage());
	// }
	// });
	// }

	// private synchronized static void initialise() {
	// if (!isLaunched) {
	// isLaunched = true;
	//
	// synchronized (SYNCLAUNCH) {
	// new Thread(new Runnable() {
	// @Override
	// public void run() {
	// Main.launch(Main.class);
	// System.exit(0);
	// }
	// }).start();
	//
	// try {
	// SYNCLAUNCH.wait();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }

	public static void main(String[] args) {
		launch(args);

		// final WhiteBoard w = new WhiteBoard();
		//
		// w.drawLine(100, 100, 100, 600, Color.ROYALBLUE);
		// w.drawLine(-50, 0, 900, 50, Color.RED);
		// w.drawLine(20, 20, 20, 10, Color.RED);
		//
		// w.drawRectangle(0, 0, 10, 10, Color.BLACK, false, 0);
		//
		// w.drawPoint(50, 25, Color.BLUE);
		//
		// w.drawArc(400, 40, 400, 400, 480, Color.BLACK, true);
		// w.drawArc(400, 40, 400, 400, 500, Color.GOLDENROD, false);
		//
		// Rectangle r = new Rectangle(0, 0, 100, 300);
		// r.setFill(Color.RED);
		//
		// Button b = new Button("click");
		// b.setOnAction(new EventHandler<ActionEvent>() {
		// private Object obj;
		//
		// @Override
		// public void handle(ActionEvent arg0) {
		// if (obj == null) {
		// obj = w.drawCircle(100, 100, 30, Color.GREENYELLOW, true);
		// } else {
		// w.removeShape(obj);
		// obj = null;
		// }
		// }
		// });
		//
		// final Pane m = new Pane();
		//
		// Button b2 = new Button("click");
		// b2.setLayoutX(40);
		// b2.setOnAction(new EventHandler<ActionEvent>() {
		// private boolean nsize;
		//
		// @Override
		// public void handle(ActionEvent arg0) {
		// if (!nsize) {
		// nsize = true;
		// m.setMinWidth(300);
		// } else {
		// nsize = false;
		// m.setMinWidth(150);
		// }
		// }
		// });
		//
		// m.setMinWidth(100);
		// m.getChildren().addAll(r, b, b2);
		//
		// // w.setMenuePane(m);
	}
}
