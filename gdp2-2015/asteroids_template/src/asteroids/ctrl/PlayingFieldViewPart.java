package asteroids.ctrl;

import java.util.Arrays;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import asteroids.model.PlayingField;
import asteroids.model.SpaceshipModel;
import asteroids.view.PlayingFieldView;

public class PlayingFieldViewPart extends ViewPartBase<PlayingField, PlayingFieldView> {
	public PlayingFieldViewPart(PlayingField model) {
		super(model);
	}

	@Override
	public List<Object> getModelChildren() {
		SpaceshipModel spaceship = getModelElement().getSpaceship();
		return Arrays.<Object> asList(spaceship);
	}

	@Override
	public void addChildViewPart(final IViewPart childViewPart) {
		getView().getChildren().add(childViewPart.getView());
	}

	// Kovariante Rückgabetypen
	@Override
	public PlayingFieldView createView() {
		PlayingField m = getModelElement();
		PlayingFieldView v = new PlayingFieldView(m.getWidth(), m.getHeight());
		return v;
	}

	@Override
	public void activate() {
		super.activate();
		installKeyHandler();
	}

	@Override
	public void refreshView() {
		// do nothing
	}

	private void installKeyHandler() {
		// handler for key pressed / released events not handled by key nodes
		final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent keyEvent) {
				PlayingField field = getModelElement();
				SpaceshipModel spaceshipModel = field.getSpaceship();

				KeyCode keyCode = keyEvent.getCode();
				if (keyCode.equals(KeyCode.UP))
					spaceshipModel.accelerate();
				else if (keyCode.equals(KeyCode.DOWN))
					spaceshipModel.decelerate();
				else if (keyCode.equals(KeyCode.LEFT))
					spaceshipModel.turnLeft();
				else if (keyCode.equals(KeyCode.RIGHT))
					spaceshipModel.turnRight();
			}
		};

		Parent p = getView().getParent();
		p.setOnKeyPressed(keyEventHandler);
		p.requestFocus();
	}
}
