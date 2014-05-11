package javafx_praktikum.rectangle_3.utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * {@link PropertyChangeObservableImpl Diese Klasse} delegiert die Realisierung der Methoden an {@link PropertyChangeSupport}.
 * 
 * @author Frank Grimm
 */
public class PropertyChangeObservableImpl implements PropertyChangeObservable {
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}
}
