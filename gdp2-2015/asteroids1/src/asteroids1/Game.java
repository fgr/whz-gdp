package asteroids1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Frank Grimm
 */
class Game {
	private PlayingField playingField;

	public Game(Stage stage) {
		playingField = new PlayingField(stage);
	}

	public void start() {
		createAndStartGameLoop();
	}

	private void createAndStartGameLoop() {
		Duration oneFrameDuration = Duration.millis(1000 / 60);
		KeyFrame oneFrame = new KeyFrame(oneFrameDuration, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playingField.refresh();
			}
		}); // oneFrame

		// sets the game loop
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(oneFrame);
		timeline.play();
	}
}
