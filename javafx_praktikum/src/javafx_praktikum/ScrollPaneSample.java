package javafx_praktikum;

import java.text.DateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrollPaneSample extends Application {
	final ScrollPane sp = new ScrollPane();
	final Image[] images = new Image[5];
	final ImageView[] pics = new ImageView[5];
	final VBox vb = new VBox();
	final Label fileName = new Label();

	// final String[] imageNames = new String[] { "fw1.jpg", "fw2.jpg", "fw3.jpg", "fw4.jpg", "fw5.jpg" };

	@Override
	public void start(Stage stage) {
		VBox box = new VBox();
		Scene scene = new Scene(box, 180, 180);
		stage.setScene(scene);
		stage.setTitle("Scroll Pane");
		box.getChildren().addAll(sp, fileName);
		VBox.setVgrow(sp, Priority.ALWAYS);

		fileName.setLayoutX(30);
		fileName.setLayoutY(160);

		for (int i = 0; i < 5; i++) {
			TweetPane p = new TweetPane("My", "Tweet to me", new Date(), 42);
			vb.getChildren().add(0, p);
		}

		// for (int i = 0; i < 5; i++) {
		// images[i] = new Image(getClass().getResourceAsStream(imageNames[i]));
		// pics[i] = new ImageView(images[i]);
		// pics[i].setFitWidth(100);
		// pics[i].setPreserveRatio(true);
		// vb.getChildren().add(pics[i]);
		// }

		sp.setVmax(440);
		sp.setPrefSize(115, 150);
		sp.setContent(vb);
		// sp.vvalueProperty().addListener(new ChangeListener<Number>() {
		// public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
		// fileName.setText(imageNames[(new_val.intValue() - 1) / 100]);
		// }
		// });
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class TweetPane extends TitledPane {
	private final String from;
	private final String message;
	private final Date createdAt;
	private final int retweetCount;

	public TweetPane(String from, String message, Date createdAt, int retweetCount) {
		this.from = from;
		this.message = message;
		this.createdAt = createdAt;
		this.retweetCount = retweetCount;
		init();
	}

	private void init() {
		setText("Tweet from '" + from + "' at '" + DateFormat.getInstance().format(createdAt) + "'");
		{
			HBox tweetHBox = new HBox();
			Group grp1 = new Group(tweetHBox);
			setContent(grp1);
			{
				ImageView twitterView = new ImageView(new Image(getClass().getResourceAsStream("twitter.png")));
				tweetHBox.getChildren().add(twitterView);
				twitterView.setFitWidth(16);
				twitterView.setPreserveRatio(true);
			}

			{
				VBox tweetDetailsVBox = new VBox();
				Group grp2 = new Group(tweetDetailsVBox);
				tweetHBox.getChildren().add(grp2);

				{
					Label l = new Label(message);
					l.setWrapText(true);
					tweetDetailsVBox.getChildren().add(l);
				}
				{
					Label l = new Label("Retweeted: " + retweetCount);
					tweetDetailsVBox.getChildren().add(l);
				}
			}
		}
	}
}