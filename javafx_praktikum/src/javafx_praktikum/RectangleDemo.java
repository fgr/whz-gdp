package javafx_praktikum;

import java.math.BigDecimal;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import de.thomasbolz.javafx.NumberSpinner;

public class RectangleDemo extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private final int X_MIN = 0;
	private final int X_MAX = 100;
	private final int X_START = 10;

	private final int Y_MIN = 0;
	private final int Y_MAX = 150;
	private final int Y_START = 15;

	private final String AREA_FORMAT = "Area: %1$,.2f square units";

	private Slider ySldr;
	private Slider xSldr;
	private Label areaLbl;
	private Rectangle rectangle;
	private NumberSpinner xSpnr;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Rectangle/area demo");
		Scene scene = new Scene(new Group(), 450, 250);
		primaryStage.setScene(scene);
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(true);
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));

		{
			areaLbl = new Label();
			grid.add(areaLbl, 2, 0);
			areaLbl.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
		}

		{
			final NumberSpinner ySpnr = new NumberSpinner(new BigDecimal(Y_MIN), BigDecimal.ONE);
			ySldr = new Slider();

			{
				grid.add(ySpnr, 0, 0);
				ySpnr.setMaxWidth(50);
				ySpnr.numberProperty().setValue(new BigDecimal(Y_START));
				ySpnr.numberProperty().addListener(new ChangeListener<BigDecimal>() {
					@Override
					public void changed(ObservableValue<? extends BigDecimal> v, BigDecimal oldValue, BigDecimal newValue) {
						if (newValue.intValue() < Y_MIN) {
							newValue = new BigDecimal(Y_MIN);
							ySpnr.numberProperty().setValue(newValue);
						} else if (newValue.intValue() > Y_MAX) {
							newValue = new BigDecimal(Y_MAX);
							ySpnr.numberProperty().setValue(newValue);
						}
						ySldr.setValue(newValue.intValue());
						updateRectangle();
						updateArea();
					}
				});
			}

			{
				grid.add(ySldr, 0, 1);

				ySldr.setOrientation(Orientation.VERTICAL);
				ySldr.setMin(Y_MIN);
				ySldr.setMax(Y_MAX);
				ySldr.setValue(Y_START);
				ySldr.setShowTickLabels(true);
				ySldr.setShowTickMarks(true);
				ySldr.setMajorTickUnit(50);
				ySldr.setMinorTickCount(5);
				ySldr.setBlockIncrement(10);
				ySldr.valueProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> v,//
							Number oldValue, Number newValue) {
						// Slider-Wert hat sich geaendert
						ySpnr.numberProperty().set(new BigDecimal(newValue.intValue()));
						updateRectangle();
						updateArea();
					}
				});
			}
		}

		{
			xSpnr = new NumberSpinner(new BigDecimal(X_MIN), BigDecimal.ONE);
			grid.add(xSpnr, 1, 2);
			xSpnr.numberProperty().setValue(new BigDecimal(X_START));
			xSpnr.setMaxWidth(50);
			xSpnr.numberProperty().addListener(new ChangeListener<BigDecimal>() {
				@Override
				public void changed(ObservableValue<? extends BigDecimal> v, BigDecimal oldValue, BigDecimal newValue) {
					if (newValue.intValue() < X_MIN) {
						newValue = new BigDecimal(X_MIN);
						xSpnr.numberProperty().setValue(newValue);
					} else if (newValue.intValue() > X_MAX) {
						newValue = new BigDecimal(X_MAX);
						xSpnr.numberProperty().setValue(newValue);
					}
					xSldr.setValue(newValue.intValue());
					updateRectangle();
					updateArea();
				}
			});
		}

		{
			xSldr = new Slider();
			grid.add(xSldr, 2, 2);

			xSldr.setOrientation(Orientation.HORIZONTAL);
			xSldr.setMin(X_MIN);
			xSldr.setMax(X_MAX);
			xSldr.setValue(X_START);
			xSldr.setShowTickLabels(true);
			xSldr.setShowTickMarks(true);
			xSldr.setMajorTickUnit(50);
			xSldr.setMinorTickCount(5);
			xSldr.setBlockIncrement(10);
			xSldr.valueProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> v,//
						Number oldValue, Number newValue) {
					// Slider-Wert hat sich geaendert
					xSpnr.numberProperty().set(new BigDecimal(newValue.intValue()));
					updateRectangle();
					updateArea();
				}
			});

		}

		{
			BorderPane borderpane = new BorderPane();
			grid.add(borderpane, 2, 1);
			rectangle = new Rectangle(X_START, Y_START, Color.RED);
			borderpane.setCenter(rectangle);
			rectangle.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(final KeyEvent keyEvent) {
					boolean changed = false;
					double rectangleWidth = xSldr.getValue();
					double rectangleHeight = ySldr.getValue();
					KeyCode keyCode = keyEvent.getCode();
					if (keyCode.equals(KeyCode.RIGHT)) {
						// Breite des Rechtecks vergroessern
						rectangleWidth++;
						changed = true;
					} else if (keyCode.equals(KeyCode.LEFT)) {
						// Breite des Rechtecks verkleinern
						rectangleWidth--;
						changed = true;
					} else if (keyCode.equals(KeyCode.UP)) {
						// Hoehe des Rechtecks vergroessern
						rectangleHeight++;
						changed = true;
					} else if (keyCode.equals(KeyCode.DOWN)) {
						// Hoehe des Rechtecks verkleinern
						rectangleHeight--;
						changed = true;
					}

					if (changed) {
						xSldr.setValue(rectangleWidth);
						ySldr.setValue(rectangleHeight);
						updateRectangle();
						updateArea();
					}
				}
			});
			rectangle.setFocusTraversable(true);
			rectangle.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldHasFocus, Boolean newHasFocus) {
					if (newHasFocus) {
						rectangle.setStyle("-fx-stroke: blue; -fx-stroke-width: 3;");
					} else {
						rectangle.setStyle("-fx-stroke: red; -fx-stroke-width: 1;");
					}
				}
			});
			rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					rectangle.requestFocus();
				}
			});
		}

		Group root = (Group) scene.getRoot();
		root.getChildren().add(grid);

		primaryStage.show();

		updateArea();
	}

	private void updateRectangle() {
		double xValue = xSldr.getValue();
		rectangle.setWidth(xValue);
		double yValue = ySldr.getValue();
		rectangle.setHeight(yValue);
	}

	private void updateArea() {
		double xValue = xSldr.getValue();
		double yValue = ySldr.getValue();
		double area = xValue * yValue;
		areaLbl.setText(String.format(AREA_FORMAT, area));
	}
}
