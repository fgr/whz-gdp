package asteroids.ctrl;

import java.util.List;

import javafx.scene.Node;

public interface ViewPart {
	Object getModelElement();

	List<Object> getModelChildren();

	Node getView();

	void refreshView();

	ViewPart getParentViewPart();

	void addChildViewPart(ViewPart childViewPart);

	void activate();
	void passivate();
}
