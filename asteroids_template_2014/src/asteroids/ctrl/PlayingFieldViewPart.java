package asteroids.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import asteroids.model.PlayingField;
import asteroids.model.SpaceshipModel;
import asteroids.util.JavaFxPlatformUtils;
import asteroids.view.PlayingFieldView;

public class PlayingFieldViewPart extends
		ViewPartBase<PlayingField, PlayingFieldView> {
	public PlayingFieldViewPart(PlayingField modelElement) {
		super(modelElement);
	}

	@Override
	public void activate() {
		super.activate();
		installKeyHandler();
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

	@Override
	public List<Object> getModelChildren() {
		ArrayList<Object> modelChildren = new ArrayList<>();

		SpaceshipModel spaceship = getModelElement().getSpaceship();
		if (spaceship != null)
			modelChildren.add(spaceship);

		// TODO Asteroiden hinzufuegen

		return modelChildren;
	}

	@Override
	public void refreshView() {
		// do nothing
	}

	@Override
	public void addChildViewPart(final ViewPart childViewPart) {
		try {
			JavaFxPlatformUtils.runAndWait(new Runnable() {
				@Override
				public void run() {
					getView().getChildren().add(childViewPart.getView());
				}
			});
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Could not add child ViewPart", e);
		}
	}

	@Override
	protected PlayingFieldView createView() {
		PlayingField model = getModelElement();
		PlayingFieldView view = new PlayingFieldView(//
				model.getWidth(), model.getHeight());
		return view;
	}
}
