package javafx_praktikum.rectangle_2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx_praktikum.rectangle_2.ctrl.AreaController;
import javafx_praktikum.rectangle_2.model.AreaModel;
import javafx_praktikum.rectangle_2.view.AreaView;
import javafx_praktikum.rectangle_2.view.AreaView.AreaCalcViewConfig;
import javafx_praktikum.rectangle_2.view.AreaView.DimensionViewConfig;

public class RectangleDemo2 extends Application {
	private static final int WIDTH_MIN = 0;
	private static final int WIDTH_MAX = 100;
	private static final int WIDTH_START = 10;

	private static final int HEIGHT_MIN = 0;
	private static final int HEIGHT_MAX = 150;
	private static final int HEIGHT_START = 15;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Rectangle/area demo");
		Scene scene = new Scene(new Group(), 450, 250);
		primaryStage.setScene(scene);

		int width = WIDTH_START;
		int height = HEIGHT_START;

		AreaCalcViewConfig cfg = new AreaCalcViewConfig(//
				new DimensionViewConfig(WIDTH_MIN, WIDTH_MAX, width), //
				new DimensionViewConfig(HEIGHT_MIN, HEIGHT_MAX, height));

		AreaModel model = new AreaModel(width, height);
		AreaView view = new AreaView(cfg);

		@SuppressWarnings("unused")
		AreaController crtl = new AreaController(model, view);

		Group root = (Group) scene.getRoot();
		root.getChildren().add(view);

		primaryStage.show();
	}
}
