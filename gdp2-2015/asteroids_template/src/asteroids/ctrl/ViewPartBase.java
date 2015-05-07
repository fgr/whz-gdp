package asteroids.ctrl;

import javafx.scene.Node;

/**
 * Abstract base class which implement common methods required by concrete view
 * part implementations (such as the {@link asteroids.ctrl.SpaceshipViewPart}).
 * It should thus be extended by these classes.
 * 
 * @param <TModelElement>
 *            Type parameter (i.e., class or interface) defining the type of the
 *            model element. Has to be provided by classes extending
 *            {@link ViewPartBase}
 * @param <TView>
 *            Type parameter (i.e., class or interface) defining the type of the
 *            view. Has to be provided by classes extending {@link ViewPartBase}
 * 
 * @author Frank Grimm
 */
public abstract class ViewPartBase<TModelElement, TView extends Node> //
		implements IViewPart {
	private final TModelElement modelElement;
	private IViewPart parentViewPart;
	private TView view;

	public ViewPartBase(TModelElement modelElement) {
		this.modelElement = modelElement;
	}

	@Override
	public TModelElement getModelElement() {
		return modelElement;
	}

	public void setParentViewPart(IViewPart parentViewPart) {
		this.parentViewPart = parentViewPart;
	}

	@Override
	public IViewPart getParentViewPart() {
		return parentViewPart;
	}

	@Override
	public void activate() {
		getView().visibleProperty().set(true);
	}

	@Override
	public void passivate() {
		getView().visibleProperty().set(false);
	}

	@Override
	final public TView getView() {
		if (view == null) {
			view = createView();
			if (view == null)
				throw new IllegalStateException("Could not create view for ViewPart: " + this);
		}
		return view;
	}

	abstract protected TView createView();
}
