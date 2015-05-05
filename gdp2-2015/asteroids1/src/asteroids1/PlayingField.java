package asteroids1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import shapes.Point;
import shapes.Polygon;

/**
 * 
 * <em>Achtung:</em> Das ist kein guter Quellcode! Diese Klasse (
 * {@link PlayingField}) hat zu viele Verantwortlichkeiten, d.h., sie kümmert
 * sich um zu viele Dinge. Wir werden diesen Nachteil in den folgenden Versionen
 * des Asteroid-Spiels beheben und besseren Quellcode schreiben.
 * 
 * @author Frank Grimm
 */
class PlayingField {
	private Canvas canvas;
	private Polygon spaceshipShape;
	private double spaceshipRotationAngle, spaceshipDirection;
	private double spaceshipSpeed;

	public PlayingField(Stage stage) {
		Objects.requireNonNull(stage);

		Point spaceshipCenter = new Point(100, 100);
		List<Point> points = Arrays.asList(//
				new Point(spaceshipCenter.getX(), spaceshipCenter.getY()), //
				new Point(spaceshipCenter.getX() - 10, spaceshipCenter.getY() + 40), //
				new Point(spaceshipCenter.getX() + 10, spaceshipCenter.getY() + 40));

		spaceshipShape = new Polygon(points, null);

		spaceshipRotationAngle = 0;
		spaceshipDirection = 180;

		spaceshipSpeed = 0;

		Group root = new Group();
		canvas = new Canvas(600, 500);

		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
				case UP: {
					accelerateSpaceship();
					keyEvent.consume();
					break;
				}
				case DOWN: {
					decelerateSpaceship();
					keyEvent.consume();
					break;
				}
				case LEFT: {
					turnSpaceshipLeft();
					keyEvent.consume();
					break;
				}
				case RIGHT: {
					turnSpaceshipRigth();
					keyEvent.consume();
					break;
				}
				default:
					break;
				}
			}
		});
		stage.setScene(scene);
	}

	public void refresh() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setFill(Color.GREEN);
		gc.fillRect(spaceshipShape.centerOfMass().getX(), spaceshipShape.centerOfMass().getY(), 1, 1);

		if (spaceshipRotationAngle != 0)
			spaceshipShape.rotate(spaceshipShape.centerOfMass(), spaceshipRotationAngle);
		spaceshipDirection -= spaceshipRotationAngle;
		if (spaceshipDirection > 360)
			spaceshipDirection = 0;
		spaceshipRotationAngle = 0;

		double rad = Math.toRadians(spaceshipDirection);
		double deltaX = Math.sin(rad) * spaceshipSpeed;
		double deltaY = Math.cos(rad) * spaceshipSpeed;
		spaceshipShape.move(deltaX, deltaY);

		spaceshipShape.draw(gc);
	}

	void accelerateSpaceship() {
		spaceshipSpeed += 1;
	}

	void decelerateSpaceship() {
		spaceshipSpeed -= 1;
	}

	void turnSpaceshipLeft() {
		spaceshipRotationAngle -= 1;
		if (nearlyEqual(spaceshipRotationAngle, 0, 0) || spaceshipRotationAngle < 0) {
			spaceshipRotationAngle = 359;
		}
	}

	public void turnSpaceshipRigth() {
		spaceshipRotationAngle += 1;
		if (nearlyEqual(spaceshipRotationAngle, 360, 0) || spaceshipRotationAngle > 360) {
			spaceshipRotationAngle = 1;
		}
	}

	/**
	 * @see http://floating-point-gui.de/errors/comparison/
	 */
	private static boolean nearlyEqual(double a, double b, double epsilon) {
		final double absA = Math.abs(a);
		final double absB = Math.abs(b);
		final double diff = Math.abs(a - b);

		if (a == b) { // shortcut, handles infinities
			return true;
		} else if (a == 0 || b == 0 || diff < Double.MIN_NORMAL) {
			// a or b is zero or both are extremely close to it
			// relative error is less meaningful here
			return diff < (epsilon * Double.MIN_NORMAL);
		} else { // use relative error
			return diff / Math.min((absA + absB), Double.MAX_VALUE) < epsilon;
		}
	}
}
