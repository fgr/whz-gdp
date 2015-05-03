package javafx_praktikum.rectangle_4a;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx_praktikum.rectangle_4a.ctrl.AreaViewController4a;
import javafx_praktikum.rectangle_4a.model.AreaModel4a;
import javafx_praktikum.rectangle_4a.view.AreaView4a;
import javafx_praktikum.rectangle_4a.view.AreaView4a.AreaCalcViewConfig4a;
import javafx_praktikum.rectangle_4a.view.AreaView4a.DimensionViewConfig4a;

public class RectangleDemo4a extends Application {
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

		AreaCalcViewConfig4a cfg = new AreaCalcViewConfig4a(//
				new DimensionViewConfig4a(WIDTH_MIN, WIDTH_MAX, width), //
				new DimensionViewConfig4a(HEIGHT_MIN, HEIGHT_MAX, height));

		AreaModel4a model = new AreaModel4a(width, height);
		AreaView4a view = new AreaView4a(cfg);

		@SuppressWarnings("unused")
		AreaViewController4a crtl = new AreaViewController4a(model, view);
		Group root = (Group) scene.getRoot();
		root.getChildren().add(view);

		primaryStage.show();
	}
}
