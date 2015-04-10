package asteroids.ctrl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.Group;
import asteroids.util.ObjectHelpers;

public class GameController {
	private final ViewPartFactory viewPartFactory;
	private final ViewPart rootViewPart;

	Map<Object, ViewPart> modelToViewPartMap = new HashMap<>();

	public GameController(Object rootModelElement,
			ViewPartFactory viewPartFactory, Group view) {
		ObjectHelpers.assertNotNull(rootModelElement);
		ObjectHelpers.assertNotNull(viewPartFactory);
		ObjectHelpers.assertNotNull(view);

		this.viewPartFactory = viewPartFactory;

		rootViewPart = createViewPart(rootModelElement, null);

		view.getChildren().add(rootViewPart.getView());
		rootViewPart.activate();
	}

	public void activate() {
		createGameLoopThread();
	}

	public ViewPart getRootViewPart() {
		return rootViewPart;
	}

	private ViewPart createViewPart(Object modelElement, ViewPart parentViewPart) {
		if (modelToViewPartMap.containsKey(modelElement))
			throw new IllegalArgumentException(
					"ViewPart for model element already exists: "
							+ modelElement);

		// (1) create ViewPart based on the model element it controls ...
		ViewPart viewPart = viewPartFactory.createViewPart(modelElement);
		if (viewPart == null)
			throw new IllegalArgumentException(
					"Could not create ViewPart for model element: "
							+ modelElement);

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

	private void createGameLoopThread() {
		Thread gameLoopThread = new Thread() {
			/** 25 frames per second, i.e., 1 frame every 40 milliseconds. */
			private static final int TIME_BUDGET__MILLISEC = 40;

			@Override
			public void run() {
				while (true) {
					long startTime = System.currentTimeMillis();

					// update ViewParts (i.e., the ViewParts map) according to
					// the underlying model elements
					updateViewParts();

					// refresh the updated ViewParts' view
					for (ViewPart viewPart : modelToViewPartMap.values())
						viewPart.refreshView();

					waitUntilNextFrame(startTime);
				}
			}

			private void waitUntilNextFrame(long startTime) {
				try {
					long timeSpent = System.currentTimeMillis() - startTime;
					long timeToWait = TIME_BUDGET__MILLISEC - timeSpent;
					if (timeToWait <= 0) {
						System.err.println("Skipping frame at " + startTime);
						return;
					}
					Thread.sleep(timeToWait);
				} catch (InterruptedException e) {
					// ignore
					System.err.println(e);
				}
			}

			private void updateViewParts() {
				Set<Object> oldModelElems = new HashSet<>(
						modelToViewPartMap.keySet());

				Set<Object> updatedModelElems = updateViewParts(rootViewPart);

				// get deleted model elements
				oldModelElems.removeAll(updatedModelElems);
				// ... and remove their obsolete ViewParts
				for (Object oldModelElem : oldModelElems) {
					ViewPart viewPart = modelToViewPartMap.remove(oldModelElem);
					viewPart.passivate();
				}
			}

			/**
			 * Returns the model elements for which {@link ViewPart}s were
			 * {@link #createViewPart} newly created or already existed.
			 * 
			 * @param viewPart
			 *            The {@link ViewPart} to
			 * @return
			 */
			private Set<Object> updateViewParts(ViewPart viewPart) {
				Set<Object> modelElems = new HashSet<>();
				modelElems.add(viewPart.getModelElement());
				for (Object modelChild : viewPart.getModelChildren()) {
					ViewPart childViewPart = modelToViewPartMap.get(modelChild);
					modelElems.add(modelChild);
					if (childViewPart == null)
						childViewPart = createViewPart(modelChild, viewPart);
					modelElems.addAll(updateViewParts(childViewPart));
				}
				return modelElems;
			}
		};
		gameLoopThread.start();
	}
}
