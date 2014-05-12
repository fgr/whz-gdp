package javafx_praktikum.rectangle_3.model;

import java.beans.PropertyChangeListener;

import javafx_praktikum.rectangle_3.utils.PropertyChangeObservable;
import javafx_praktikum.rectangle_3.utils.PropertyChangeObservableImpl;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Die Klasse {@link AreaModel} demonstriert die Verwendung des Delegation-Patterns (siehe {@link AreaModel}.
 * 
 * @author Frank Grimm
 */
@ThreadSafe
public class AreaModel implements PropertyChangeObservable {
	public static final String PROPERTY_WIDTH = "width";
	public static final String PROPERTY_HEIGHT = "height";
	public static final String PROPERTY_AREA = "area";

	@GuardedBy("this")
	private double width;
	@GuardedBy("this")
	private double height;

	@GuardedBy("this")
	private final PropertyChangeObservableImpl observalbeDelegate = new PropertyChangeObservableImpl();

	public AreaModel(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public synchronized double getWidth() {
		return width;
	}

	public synchronized double getHeight() {
		return height;
	}

	public synchronized double getArea() {
		return width * height;
	}

	public synchronized void changeDimensions(double deltaWidth, double deltaHeight) {
		width += deltaWidth;
		height += deltaHeight;
		notifyChange();
	}

	public synchronized void updateDimensions(double newWidth, double newHeight) {
		width = newWidth;
		height = newHeight;
		notifyChange();
	}

	@Override
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		observalbeDelegate.addPropertyChangeListener(listener);
	}

	@Override
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		observalbeDelegate.removePropertyChangeListener(listener);
	}

	private void notifyChange() {
		observalbeDelegate.firePropertyChange(PROPERTY_WIDTH, null, width);
		observalbeDelegate.firePropertyChange(PROPERTY_HEIGHT, null, height);
		observalbeDelegate.firePropertyChange(PROPERTY_AREA, null, getArea());
	}
}
