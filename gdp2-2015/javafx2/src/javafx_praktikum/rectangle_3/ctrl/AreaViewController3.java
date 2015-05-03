package javafx_praktikum.rectangle_3.ctrl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx_praktikum.rectangle_3.model.AreaModel3;
import javafx_praktikum.rectangle_3.view.AreaView3;
import javafx_praktikum.rectangle_3.view.AreaView3.AreaCalcViewObserver;

public class AreaViewController3 implements AreaCalcViewObserver, PropertyChangeListener {
	private final AreaModel3 model;
	private final AreaView3 view;

	public AreaViewController3(AreaModel3 model, AreaView3 view) {
		this.model = model;
		this.view = view;
		model.addPropertyChangeListener(this);
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

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (AreaModel3.PROPERTY_WIDTH.equals(e.getPropertyName()))
			view.updateRectangleDimensions(model.getWidth(), model.getHeight());
		updateView();
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
		view.updateRectangleArea(model.getArea());
	}
}
