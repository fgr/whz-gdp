package javafx_praktikum.rectangle_3.utils;

import java.beans.PropertyChangeListener;

/**
 * @author Frank Grimm
 */
public interface PropertyChangeObservable {
	void addPropertyChangeListener(PropertyChangeListener listener);

	void removePropertyChangeListener(PropertyChangeListener listener);
}
