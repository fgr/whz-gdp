package javafx_praktikum.rectangle_4a.view;

import java.util.HashSet;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx_praktikum.rectangle_4a.view.AreaView4a.AreaCalcViewObserver4a.EventType;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;

/**
 * View für die Eingabe der Rechteck-Dimensionen, Darstellung des Rechtecks und Ausgabe des berechneten Flächeninhalts.
 * 
 * @author Frank Grimm
 */
@NotThreadSafe
public final class AreaView4a extends GridPane {
	/**
	 * Repraesentiert {@link #min Minimum}, {@link #max Maxium} und {@link #start Anfangswert} fuer die Anzeige einer Dimensionsgroesse.
	 */
	public static final class DimensionViewConfig4a {
		public final int min;
		public final int max;
		public final int start;

		public DimensionViewConfig4a(int min, int max, int start) {
			this.min = min;
			this.max = max;
			this.start = start;
		}
	}

	/**
	 * Repraesentiert die {@link DimensionViewConfig4a Dimensionskonfiguration} fuer die {@link #xDim Breite} und {@link #yDim Hoehe} der
	 * Rechtecksanzeige.
	 */
	public static final class AreaCalcViewConfig4a {
		public final DimensionViewConfig4a xDim;
		public final DimensionViewConfig4a yDim;

		public AreaCalcViewConfig4a(DimensionViewConfig4a xDim, DimensionViewConfig4a yDim) {
			this.xDim = xDim;
			this.yDim = yDim;
		}
	}

	public static interface AreaCalcViewObserver4a {
		public static enum EventType {
			/** Relative Aenderung der Breite. */
			CHANGE_WIDTH_BY, //
			/** Absolute Aenderung der Breite. */
			SET_WIDTH_TO, //
			/** Relative Aenderung der Hoehe. */
			CHANGE_HEIGHT_BY, //
			/** Absolute Aenderung der Hoehe. */
			SET_HEIGHT_TO,
		}

		void onViewEvent(EventType type, Object value);
	}

	private final String AREA_FORMAT = "Area: %1$,.2f square units";

	@GuardedBy("this")
	private Set<AreaCalcViewObserver4a> observers = new HashSet<>();

	private Spinner<Integer> heightSpnr;
	private Slider heightSldr;
	private Spinner<Integer> widthSpnr;
	private Slider widthSldr;
	private Label areaLbl;
	private Rectangle rectangle;

	public AreaView4a(AreaCalcViewConfig4a cfg) {
		createControls(cfg);
	}

	public synchronized void addObserver(AreaCalcViewObserver4a o) {
		observers.add(o);
	}

	public synchronized void removeObserver(AreaCalcViewObserver4a o) {
		observers.remove(o);
	}

	public void updateRectangleWidth(double width) {
		widthSpnr.getValueFactory().setValue((int) width);
		widthSldr.setValue(width);
		rectangle.setWidth(width);
	}

	public void updateRectangleHeight(double height) {
		heightSpnr.getValueFactory().setValue((int) height);
		heightSldr.setValue(height);
		rectangle.setHeight(height);
	}

	public void updateRectangleArea(double area) {
		areaLbl.setText(String.format(AREA_FORMAT, area));
	}

	private void createControls(final AreaCalcViewConfig4a cfg) {
		setGridLinesVisible(true);
		setVgap(4);
		setHgap(10);
		setPadding(new Insets(5, 5, 5, 5));

		{
			areaLbl = new Label();
			add(areaLbl, 2, 0);
			areaLbl.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
		}

		{
			heightSpnr = createSpinner(cfg.yDim);
			// heightSpnr.getValueFactory().valueProperty().bindBidirectional(heightProp.asObject());
			add(heightSpnr, 0, 0);
		}

		{
			heightSldr = createSlider(Orientation.VERTICAL, cfg.yDim);
			// heightSldr.valueProperty().bindBidirectional(heightProp);
			add(heightSldr, 0, 1);
		}

		{
			widthSpnr = createSpinner(cfg.xDim);
			add(widthSpnr, 1, 2);
		}

		{
			widthSldr = createSlider(Orientation.HORIZONTAL, cfg.xDim);
			add(widthSldr, 2, 2);
		}

		{
			BorderPane borderpane = new BorderPane();
			add(borderpane, 2, 1);
			rectangle = new Rectangle(cfg.xDim.start, cfg.yDim.start, Color.RED);
			borderpane.setCenter(rectangle);
			rectangle.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(final KeyEvent keyEvent) {
					KeyCode keyCode = keyEvent.getCode();
					if (keyCode.equals(KeyCode.RIGHT)) {
						// Breite des Rechtecks vergroessern
						notifyObservers(EventType.CHANGE_WIDTH_BY, 1);
					} else if (keyCode.equals(KeyCode.LEFT)) {
						// Breite des Rechtecks verkleinern
						notifyObservers(EventType.CHANGE_WIDTH_BY, -1);
					} else if (keyCode.equals(KeyCode.UP)) {
						// Hoehe des Rechtecks vergroessern
						notifyObservers(EventType.CHANGE_HEIGHT_BY, 1);
					} else if (keyCode.equals(KeyCode.DOWN)) {
						// Hoehe des Rechtecks verkleinern
						notifyObservers(EventType.CHANGE_HEIGHT_BY, -1);
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
	}

	private <T> Spinner<T> createSpinner(final DimensionViewConfig4a cfg) {
		final Spinner<T> spnr = new Spinner<T>(cfg.min, cfg.max, cfg.start);
		spnr.setMaxWidth(75);

		return spnr;
	}

	private Slider createSlider(Orientation orientation, DimensionViewConfig4a cfg) {
		Slider sldr = new Slider();
		sldr.setOrientation(orientation);
		sldr.setMin(cfg.min);
		sldr.setMax(cfg.max);
		sldr.setValue(cfg.start);
		sldr.setShowTickLabels(true);
		sldr.setShowTickMarks(true);
		sldr.setMajorTickUnit(50);
		sldr.setMinorTickCount(5);
		sldr.setBlockIncrement(10);
		return sldr;
	}

	private synchronized void notifyObservers(EventType type, Object value) {
		for (AreaCalcViewObserver4a o : observers)
			o.onViewEvent(type, value);
	}
}
