package javafx_praktikum.rectangle_2.model;

import java.util.Observable;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class AreaModel2 extends Observable {
	@GuardedBy("this")
	private double width;
	@GuardedBy("this")
	private double height;

	public AreaModel2(double width, double height) {
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
		notifyObservers();
	}

	public synchronized void updateDimensions(double newWidth, double newHeight) {
		width = newWidth;
		height = newHeight;
		notifyObservers();
	}

	@Override
	public synchronized void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
}
