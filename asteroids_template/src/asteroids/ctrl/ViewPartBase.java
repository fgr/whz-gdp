package asteroids.ctrl;

import javafx.scene.Node;

public abstract class ViewPartBase<ModelElementType, ViewType extends Node> //
		implements ViewPart {
	private final ModelElementType modelElement;
	private ViewPart parentViewPart;
	private ViewType view;

	public ViewPartBase(ModelElementType modelElement) {
		this.modelElement = modelElement;
	}

	@Override
	public ModelElementType getModelElement() {
		return modelElement;
	}

	public void setParentViewPart(ViewPart parentViewPart) {
		this.parentViewPart = parentViewPart;
	}

	@Override
	public ViewPart getParentViewPart() {
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
	final public ViewType getView() {
		if (view == null) {
			view = createView();
			if (view == null)
				throw new IllegalStateException(
						"Could not create view for ViewPart: " + this);
		}
		return view;
	}

	abstract protected ViewType createView();
}
