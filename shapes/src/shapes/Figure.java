package shapes;

import java.util.ArrayList;
import java.util.Arrays;

// import java.awt.Color;
import javafx.scene.paint.Color;
import whiteBoard.WhiteBoard;

public class Figure implements Drawable {
	private final ArrayList<Drawable> shapes;

	public Figure() {
		shapes = new ArrayList<Drawable>();
	}

	/**
	 * Fuegt eine {@link Shape} zu dieser {@link Figure} hinzu.
	 */
	public Figure addShape(Drawable drawable) {
		shapes.add(drawable);
		return this;
	}

	/**
	 * Bewegt die {@link Figure}.
	 */
	@Override
	public void move(double deltaX, double deltaY) {
		for (Drawable drawable : shapes)
			drawable.move(deltaX, deltaY);
	}

	/**
	 * Zeichnet die {@link Figure}.
	 */
	@Override
	public void draw() {
		for (Drawable drawable : shapes)
			drawable.draw();
	}

	@Override
	public void rotate(Point centre, double degrees) {
		for (Drawable s : shapes)
			s.rotate(centre, degrees);
	}

	/**
	 * Erstellt eine Schneemann-{@link Figure}.
	 */
	public static Figure createSnowman(WhiteBoard wb) {
		Figure hat = new Figure();

		hat.shapes.add(new Rectangle(//
				new Point(360, 294), 80, 6, Color.BLACK, true, wb));
		hat.shapes.add(new Rectangle(//
				new Point(375, 339), 50, 45, Color.BLACK, true, wb));

		Figure figure = new Figure();
		figure.shapes.addAll(Arrays.asList(//
				new Circle(60, new Point(400, 100), Color.CYAN, true, wb), //
				new Circle(45, new Point(400, 190), Color.CYAN, true, wb), //
				new Circle(30, new Point(400, 260), Color.CYAN, true, wb), //
				new Circle(4, new Point(400, 80), Color.BLACK, true, wb), //
				new Circle(4, new Point(400, 120), Color.BLACK, true, wb), //
				new Circle(4, new Point(400, 175), Color.BLACK, true, wb), //
				new Circle(4, new Point(400, 205), Color.BLACK, true, wb), //
				new Circle(4, new Point(390, 270), Color.BLACK, true, wb), //
				new Circle(4, new Point(410, 270), Color.BLACK, true, wb)) //
				);

		figure.shapes.add(hat);

		figure.draw();

		return figure;
	}
}