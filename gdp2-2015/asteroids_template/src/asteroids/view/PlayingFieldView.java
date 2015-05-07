package asteroids.view;

import javafx.scene.layout.Pane;

/**
 * A {@link PlayingFieldView} instance is responsible for visualising the
 * playing field model element. It is a JavaFX {@link Pane}.
 * 
 * @author Frank Grimm
 */
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
