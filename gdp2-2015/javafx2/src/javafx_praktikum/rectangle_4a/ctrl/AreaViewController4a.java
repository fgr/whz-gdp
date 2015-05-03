package javafx_praktikum.rectangle_4a.ctrl;

import javafx_praktikum.rectangle_4a.model.AreaModel4a;
import javafx_praktikum.rectangle_4a.view.AreaView4a;
import javafx_praktikum.rectangle_4a.view.AreaView4a.AreaCalcViewObserver4a;

public class AreaViewController4a implements AreaCalcViewObserver4a {
	private final AreaModel4a model;
	private final AreaView4a view;

	public AreaViewController4a(AreaModel4a model, AreaView4a view) {
		this.model = model;
		this.view = view;
		model.registerListener((observable, oldValue, newValue) -> updateView());
		view.addObserver(this);
	}

	@Override
	public void onViewEvent(EventType type, Object value) {
		switch (type) {
		case CHANGE_HEIGHT_BY: {
			double deltaHeight = toDouble(value);
			double newHeight = deltaHeight + model.getHeight();
			model.setDimensions(0, newHeight);
			break;
		}
		case CHANGE_WIDTH_BY: {
			double deltaWidth = toDouble(value);
			double newWidth = deltaWidth + model.getWidth();
			model.setDimensions(newWidth, 0);
			break;
		}
		case SET_HEIGHT_TO: {
			double newHeigth = toDouble(value);
			model.setDimensions(model.getWidth(), newHeigth);
			break;
		}
		case SET_WIDTH_TO: {
			double newWidth = toDouble(value);
			model.setDimensions(newWidth, model.getHeight());
			break;
		}
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
		view.updateRectangleHeight(model.getHeight());
		view.updateRectangleWidth(model.getWidth());
		view.updateRectangleArea(model.getArea());
	}
}
