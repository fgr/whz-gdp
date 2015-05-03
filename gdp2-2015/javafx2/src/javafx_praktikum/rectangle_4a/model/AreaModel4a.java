package javafx_praktikum.rectangle_4a.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Die Klasse {@link AreaModel4a} demonstriert die Verwendung TODO (siehe {@link AreaModel4a}.
 * 
 * @author Frank Grimm
 */
@ThreadSafe
public class AreaModel4a {
	@GuardedBy("this")
	private DoubleProperty widthProp = new SimpleDoubleProperty();
	@GuardedBy("this")
	private DoubleProperty heightProp = new SimpleDoubleProperty();
	@GuardedBy("this")
	private DoubleProperty areaProp = new SimpleDoubleProperty();

	public AreaModel4a(double width, double height) {
		setDimensions(width, height);
	}

	public void registerListener(ChangeListener<? super Number> l) {
		widthProp.addListener(l);
		heightProp.addListener(l);
		areaProp.addListener(l);
	}

	public synchronized double getWidth() {
		return widthProp.doubleValue();
	}

	public synchronized double getHeight() {
		return heightProp.doubleValue();
	}

	public synchronized double getArea() {
		return areaProp.doubleValue();
	}

	public synchronized void setDimensions(double newWidth, double newHeight) {
		widthProp.set(newWidth);
		heightProp.set(newHeight);
		double area = newWidth * newWidth;
		areaProp.set(area);
	}
}
