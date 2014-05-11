package javafx_praktikum.rectangle_2.view;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;
import de.thomasbolz.javafx.NumberSpinner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx_praktikum.rectangle_2.view.AreaCalcView.AreaCalcViewObserver.EventType;

/**
 * View für die Eingabe der Reckteck-Dimensionen, Darstellung des Rechtecks und Ausgabe des berechneten Flächeninhalts.
 * 
 * @author Frank Grimm
 */
@NotThreadSafe
public final class AreaCalcView extends GridPane {
	/**
	 * Repraesentiert {@link #min Minimum}, {@link #max Maxium} und {@link #start Anfangswert} fuer die Anzeige einer Dimensionsgroesse.
	 */
	public static final class DimensionViewConfig {
		public final int min;
		public final int max;
		public final int start;

		public DimensionViewConfig(int min, int max, int start) {
			this.min = min;
			this.max = max;
			this.start = start;
		}
	}

	/**
	 * Repraesentiert die {@link DimensionViewConfig Dimensionskonfiguration} fuer die {@link #xDim Breite} und {@link #yDim Hoehe} der
	 * Rechtecksanzeige.
	 */
	public static final class AreaCalcViewConfig {
		public final DimensionViewConfig xDim;
		public final DimensionViewConfig yDim;

		public AreaCalcViewConfig(DimensionViewConfig xDim, DimensionViewConfig yDim) {
			this.xDim = xDim;
			this.yDim = yDim;
		}
	}

	public static interface AreaCalcViewObserver {
		public static enum EventType {
			/** Relative Aenderung der Breite. */
			CHANGE_WIDTH_BY, //
			/** Absulute Aenderung der Breite. */
			SET_WIDTH_TO, //
			/** Relative Aenderung der Hoehe. */
			CHANGE_HEIGHT_BY, //
			/** Absulute Aenderung der Hoehe. */
			SET_HEIGHT_TO,
		}

		void onViewEvent(EventType type, Object value);
	}

	private final String AREA_FORMAT = "Area: %1$,.2f square units";

	@GuardedBy("this")
	private Set<AreaCalcViewObserver> observers = new HashSet<>();

	private NumberSpinner heightSpnr;
	private Slider heightSldr;
	private NumberSpinner widthSpnr;
	private Slider widthSldr;
	private Label areaLbl;
	private Rectangle rectangle;

	public AreaCalcView(AreaCalcViewConfig cfg) {
		createControls(cfg);
	}

	public void updateRectangleDimensions(double width, double height) {
		widthSpnr.setNumber(new BigDecimal(width));
		widthSldr.setValue(width);
		heightSpnr.setNumber(new BigDecimal(height));
		heightSldr.setValue(height);
		rectangle.setWidth(width);
		rectangle.setHeight(height);
	}

	public void updateArea(double area) {
		areaLbl.setText(String.format(AREA_FORMAT, area));
	}

	public synchronized void addObserver(AreaCalcViewObserver o) {
		observers.add(o);
	}

	public synchronized void removeObserver(AreaCalcViewObserver o) {
		observers.remove(o);
	}

	private synchronized void notifyObserver(EventType type, Object value) {
		for (AreaCalcViewObserver o : observers)
			o.onViewEvent(type, value);
	}

	private void createControls(final AreaCalcViewConfig cfg) {
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
			heightSpnr = createSpinner(cfg.yDim, new Runnable() {
				@Override
				/** Hoehe des Rechtecks wurde durch {@link AreaCalcView#heightSpnr} geaendert. */
				public void run() {
					double newHeight = heightSpnr.numberProperty().get().doubleValue();
					notifyObserver(EventType.SET_HEIGHT_TO, newHeight);
				}
			});
			add(heightSpnr, 0, 0);

			{
				heightSldr = createSlider(Orientation.VERTICAL, cfg.yDim, new Runnable() {
					@Override
					/** Hoehe des Rechtecks wurde durch {@link AreaCalcView#heightSldr} geaendert. */
					public void run() {
						double newHeight = heightSldr.getValue();
						notifyObserver(EventType.SET_HEIGHT_TO, newHeight);
					}
				});
				add(heightSldr, 0, 1);
			}
		}

		{
			widthSpnr = createSpinner(cfg.xDim, new Runnable() {
				@Override
				/** Breite des Rechtecks wurde durch {@link AreaCalcView#widthSpnr} geaendert. */
				public void run() {
					double newWidth = widthSpnr.numberProperty().get().doubleValue();
					notifyObserver(EventType.SET_WIDTH_TO, newWidth);
				}
			});
			add(widthSpnr, 1, 2);
		}

		{
			widthSldr = createSlider(Orientation.HORIZONTAL, cfg.xDim, new Runnable() {
				@Override
				/** Breite des Rechtecks wurde durch {@link AreaCalcView#widthSldr} geaendert. */
				public void run() {
					double newWidth = widthSldr.getValue();
					notifyObserver(EventType.SET_WIDTH_TO, newWidth);
				}
			});
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
						notifyObserver(EventType.CHANGE_WIDTH_BY, 1);
					} else if (keyCode.equals(KeyCode.LEFT)) {
						// Breite des Rechtecks verkleinern
						notifyObserver(EventType.CHANGE_WIDTH_BY, -1);
					} else if (keyCode.equals(KeyCode.UP)) {
						// Hoehe des Rechtecks vergroessern
						notifyObserver(EventType.CHANGE_HEIGHT_BY, 1);
					} else if (keyCode.equals(KeyCode.DOWN)) {
						// Hoehe des Rechtecks verkleinern
						notifyObserver(EventType.CHANGE_HEIGHT_BY, -1);
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

	private NumberSpinner createSpinner(final DimensionViewConfig cfg, final Runnable notifer) {
		final NumberSpinner spnr = new NumberSpinner(new BigDecimal(cfg.min), BigDecimal.ONE);
		{

			spnr.setMaxWidth(50);
			spnr.numberProperty().setValue(new BigDecimal(cfg.start));
			spnr.numberProperty().addListener(new ChangeListener<BigDecimal>() {
				@Override
				public void changed(ObservableValue<? extends BigDecimal> v, BigDecimal oldValue, BigDecimal newValue) {
					if (newValue.intValue() < cfg.min) {
						newValue = new BigDecimal(cfg.min);
						spnr.numberProperty().setValue(newValue);
					} else if (newValue.intValue() > cfg.max) {
						newValue = new BigDecimal(cfg.max);
						spnr.numberProperty().setValue(newValue);
					}
					// Spinner-Wert hat sich geaendert
					notifer.run();
				}
			});
		}

		return spnr;
	}

	private Slider createSlider(Orientation orientation, DimensionViewConfig cfg, final Runnable notifer) {
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
		sldr.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> v,//
					Number oldValue, Number newValue) {
				// Slider-Wert hat sich geaendert
				notifer.run();
			}
		});
		return sldr;
	}
}
