package asteroids.view;

import javafx.scene.layout.Pane;

public class PlayingFieldView extends Pane {
	public PlayingFieldView(double width, double height) {
		setWidth(width);
		setMinWidth(width);
		setMaxWidth(width);

		setHeight(height);
		setMinHeight(height);
		setMaxHeight(height);

		setStyle("-fx-background-color: slateblue;");
	}
}
