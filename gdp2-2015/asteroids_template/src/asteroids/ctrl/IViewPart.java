package asteroids.ctrl;

import java.util.List;

import javafx.scene.Node;

/**
 * An {@link IViewPart} is a controller connecting a
 * {@link IViewPart#getModelElement() model element} and a view representing it.
 */
public interface IViewPart {
	/** Get the model element controller by this {@link IViewPart}. */
	Object getModelElement();

	/** Get the model element's children. */
	List<Object> getModelChildren();

	/** Get the view controlled and observed by this controller */
	Node getView();

	void refreshView();

	/**
	 * Get this {@link IViewPart}'s parent {@link IViewPart. Will return null if
	 * this {@link IViewPart} is the root one.
	 */
	IViewPart getParentViewPart();

	/** Make the given {@link IViewPart} a child of this {@link IViewPart}. */
	void addChildViewPart(IViewPart childViewPart);

	/**
	 * Start to
	 * <ul>
	 * <li>observe the {@link IViewPart#getModelElement() model} for changes;</li>
	 * <li>show the view related to this {@link IViewPart};</li>
	 * <li>observe the {@link IViewPart#getView() view} for user input.</li>
	 * </ul>
	 * 
	 */
	void activate();

	/**
	 * Stop
	 * <ul>
	 * <li>observing the {@link IViewPart#getModelElement() model} for changes;</li>
	 * <li>displaying the view related to this {@link IViewPart};</li>
	 * <li>observing the {@link IViewPart#getView() view} for user input.</li>
	 * </ul>
	 * 
	 */
	void passivate();
}
