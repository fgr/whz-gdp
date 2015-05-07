package asteroids.ctrl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 * Objects of {@link GameController this class} represent the main controller.
 * It is rather a manager than a proper controller as it does not observer any
 * view or model element. Its job is to
 * <ul>
 * <li>Start the game loop ({@link GameController#createAndStartGameLoop()}).</li>
 * <li>Keep a mapping from {@link IViewPart}s (i.e., the controllers) to model
 * elements they control.</li>
 * <li>{@link GameController#createViewPart(Object, IViewPart) Create} (by means
 * of a {@link ViewPartFactory}) and delete the controllers according to the
 * model.</li>
 * </ul>
 */
public class GameController {
	private final ViewPartFactory viewPartFactory;
	private final IViewPart rootViewPart;

	Map<Object, IViewPart> modelToViewPartMap = new HashMap<>();

	public GameController(Object rootModelElement, ViewPartFactory viewPartFactory, Group view) {
		Objects.requireNonNull(rootModelElement);
		Objects.requireNonNull(viewPartFactory);
		Objects.requireNonNull(view);

		this.viewPartFactory = viewPartFactory;

		rootViewPart = createViewPart(rootModelElement, null);

		view.getChildren().add(rootViewPart.getView());
		rootViewPart.activate();
	}

	public void activate() {
		createAndStartGameLoop();
	}

	public IViewPart getRootViewPart() {
		return rootViewPart;
	}

	private IViewPart createViewPart(Object modelElement, IViewPart parentViewPart) {
		if (modelToViewPartMap.containsKey(modelElement))
			throw new IllegalArgumentException("ViewPart for model element already exists: " + modelElement);

		// (1) create ViewPart based on the model element it controls ...
		IViewPart viewPart = viewPartFactory.createViewPart(modelElement);
		if (viewPart == null)
			throw new IllegalArgumentException("Could not create ViewPart for model element: " + modelElement);

		// and (2) map it to its model element ...
		modelToViewPartMap.put(modelElement, viewPart);

		if (parentViewPart != null) {
			// and (3) add its view element to its parent view element ...
			parentViewPart.addChildViewPart(viewPart);

			// and (4) activate it
			viewPart.activate();
		}

		return viewPart;
	}

	private void createAndStartGameLoop() {
		Duration oneFrameDuration = Duration.millis(1000 / 60);
		KeyFrame oneFrame = new KeyFrame(oneFrameDuration, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// update ViewParts (i.e., the ViewParts map) according to
				// the underlying model elements
				updateViewParts();

				refreshViewParts();
			}

			private void updateViewParts() {
				Set<Object> oldModelElems = new HashSet<>(modelToViewPartMap.keySet());

				Set<Object> updatedModelElems = updateViewParts(rootViewPart);

				// get deleted model elements
				oldModelElems.removeAll(updatedModelElems);
				// ... and remove their obsolete ViewParts
				for (Object oldModelElem : oldModelElems) {
					IViewPart viewPart = modelToViewPartMap.remove(oldModelElem);
					viewPart.passivate();
				}
			}

			/**
			 * Returns the model elements for which {@link IViewPart}s were
			 * {@link #createViewPart} newly created or already existed.
			 * 
			 * @param viewPart
			 *            The {@link IViewPart} to
			 * @return
			 */
			private Set<Object> updateViewParts(IViewPart viewPart) {
				Set<Object> modelElems = new HashSet<>();
				modelElems.add(viewPart.getModelElement());
				for (Object modelChild : viewPart.getModelChildren()) {
					IViewPart childViewPart = modelToViewPartMap.get(modelChild);
					modelElems.add(modelChild);
					if (childViewPart == null)
						childViewPart = createViewPart(modelChild, viewPart);
					modelElems.addAll(updateViewParts(childViewPart));
				}
				return modelElems;
			}

			private void refreshViewParts() {
				// refresh the updated ViewParts' view
				for (IViewPart viewPart : modelToViewPartMap.values())
					viewPart.refreshView();
			}

		}); // oneFrame

		// sets the game loop
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(oneFrame);
		timeline.play();
	}
}
