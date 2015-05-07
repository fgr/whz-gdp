package asteroids.ctrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import asteroids.model.SpaceshipModel;
import asteroids.model.mshapes.MPoint;
import asteroids.view.SpaceshipView;

public class SpaceshipViewPart extends ViewPartBase<SpaceshipModel, SpaceshipView> {
	public SpaceshipViewPart(SpaceshipModel spaceship) {
		super(spaceship);
	}

	@Override
	public List<Object> getModelChildren() {
		return Collections.emptyList();
	}

	@Override
	public void addChildViewPart(IViewPart viewPart) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected SpaceshipView createView() {
		SpaceshipModel m = getModelElement();
		return new SpaceshipView(convertMPointsToDoubles(m.getPoints()));
	}

	private static List<Double> convertMPointsToDoubles(List<? extends MPoint> mpoints) {
		List<Double> points = new ArrayList<>(mpoints.size());
		for (MPoint mpoint : mpoints) {
			points.add(mpoint.getX());
			points.add(mpoint.getY());
		}
		return points;
	}

	@Override
	public void refreshView() {
		SpaceshipModel model = getModelElement();
		model.updateForNextFrame();

		SpaceshipView view = getView();
		view.updatePoints(convertMPointsToDoubles(model.getPoints()));
	}
}
