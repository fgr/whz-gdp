package javafx_praktikum.rectangle_2.ctrl;

import java.util.Observable;
import java.util.Observer;

import javafx_praktikum.rectangle_2.model.AreaModel2;
import javafx_praktikum.rectangle_2.view.AreaView2;
import javafx_praktikum.rectangle_2.view.AreaView2.AreaCalcViewObserver;

public class AreaViewController2 implements AreaCalcViewObserver {
	private final AreaModel2 model;
	private final AreaView2 view;

	public AreaViewController2(AreaModel2 model, AreaView2 view) {
		this.model = model;
		this.view = view;
		model.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				updateView();
			}
		});
		view.addObserver(this);
	}

	@Override
	public void onViewEvent(EventType type, Object value) {
		switch (type) {
		case CHANGE_HEIGHT_BY:
			double deltaHeight = toDouble(value);
			model.changeDimensions(0, deltaHeight);
			break;
		case CHANGE_WIDTH_BY:
			double deltaWidth = toDouble(value);
			model.changeDimensions(deltaWidth, 0);
			break;
		case SET_HEIGHT_TO:
			double newHeigth = toDouble(value);
			model.updateDimensions(model.getWidth(), newHeigth);
			break;
		case SET_WIDTH_TO:
			double newWidth = toDouble(value);
			model.updateDimensions(newWidth, model.getHeight());
			break;
		default:
			// ignore event
			return;
		}
	}

	private static double toDouble(Object o) {
		if (o instanceof Double)
			return (Double) o;
		else if (o instanceof Integer)
			return ((Integer) o).doubleValue();
		else
			throw new IllegalArgumentException("Cannot be converted to double: " + o);
	}

	private void updateView() {
		view.updateRectangleDimensions(model.getWidth(), model.getHeight());
		view.updateRectangleArea(model.getArea());
	}
}
