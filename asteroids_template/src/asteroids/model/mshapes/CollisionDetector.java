package asteroids.model.mshapes;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Die {@link CollisionDetector#collide}-Methoden sind Beispiele fuer multiple
 * dispatch (darf nicht mit Polymorphismus verwechselt werden!). Siehe auch <a
 * href
 * ="http://java.sun.com/docs/books/jls/third_edition/html/classes.html#8.4.9"
 * >Java Language Specification</a> und <a
 * href="http://de.wikipedia.org/wiki/Multimethode"
 * >http://de.wikipedia.org/wiki/Multimethode</a>.
 * 
 * Methode {@link CollisionDetector#lineIntersectsLine2} zeigt ein Beispiel fuer
 * den Umgang mit Rundungsfehlern (siehe Variable epsilon).
 * 
 * Die Klasse Vector2D stammt aus der Apache Commons Math Bibliothek (Version 3)
 * (siehe http://commons.apache.org/math/).
 * 
 * @author Frank Grimm, Mai 2014 (unter Verwendung von Quellen von
 *         http://paulbourke.net/geometry/)
 */
public final class CollisionDetector {
	public boolean collide(IMShape s1, IMShape s2) {
		if (s1 instanceof MPolygon)
			return collide(s1, s2);
		else if (s2 instanceof MPolygon)
			return collide(s2, s1);
		else if (s1 instanceof MCircle)
			return collide((MCircle) s1, s2);
		else if (s2 instanceof MCircle)
			return collide((MCircle) s2, s1);
		else
			throw new IllegalArgumentException(
					"Don't know how to check these objects for collision: "
							+ s1 + " and " + s2);
	}

	public boolean collide(MCircle c, IMShape s) {
		if (s instanceof MCircle)
			return circleIntersectsCircle(c, (MCircle) s);
		else if (s instanceof MLine)
			return lineIntersectsCircle((MLine) s, c);
		else
			throw new IllegalArgumentException(
					"Don't know how to check this object for collision with a MCircle: "
							+ s);
	}

	public boolean collide(MPolygon p, IMShape s) {
		List<MPoint> points = p.getPoints();
		for (int i = 0; i < points.size(); i++) {
			MPoint p1 = points.get(i);
			MPoint p2;
			if ((i + 1) == points.size())
				p2 = points.get(0);
			else
				p2 = points.get(i + 1);

			MLine l = new MLine(p1, p2);

			if (collide(l, s))
				return true;
		}

		return false;
	}

	public boolean collide(MLine l, IMShape s) {
		if (s instanceof MLine)
			return lineIntersectsLine(l, (MLine) s);
		else if (s instanceof MPolygon)
			return lineIntersectsPolygon(l, (MPolygon) s);
		else if (s instanceof MCircle)
			return lineIntersectsCircle(l, (MCircle) s);
		else
			throw new IllegalArgumentException(
					"Don't know how to check this object for collision with a MLine: "
							+ s);
	}

	public boolean lineIntersectsPolygon(MLine l, MPolygon p) {
		List<MPoint> points = p.getPoints();
		for (int i = 0; i < points.size(); i++) {
			MPoint p1 = points.get(i);
			MPoint p2;
			if ((i + 1) == points.size())
				p2 = points.get(0);
			else
				p2 = points.get(i + 1);

			MLine l2 = new MLine(p1, p2);

			if (lineIntersectsLine(l, l2))
				return true;
		}
		return false;
	}

	/** Returns cross product of given vectors. */
	private static double cross(Vector2D v1, Vector2D v2) {
		return v1.getX() * v2.getY() - v2.getX() * v1.getY();
	}

	/** @see http://paulbourke.net/geometry/lineline2d/ */
	public boolean lineIntersectsLine(MLine l1, MLine l2) {
		// TODO
		return false;
	}

	/**
	 * Beispiel fuer den Umgang mit Rundungsfehlern; siehe Variable epsilon.
	 * Hier im Beispiel ist epsilon = 10^-6.
	 * 
	 * Will man bei Berechnungen auf Rundungsfehler eingehen, so ist ein
	 * berechneter und eventuell durch Rundungsfehler ungenauer Wert a ist
	 * gleich einem anderen Wert b, wenn sich a im Bereich [b-e; b+e] befindet;
	 * also: a >= (b-e) && a <= (b+e).
	 * 
	 * 
	 * @see http://paulbourke.net/geometry/lineline2d/
	 */
	public boolean lineIntersectsLine2(MLine l1, MLine l2) {
		// epsilon (to deal with rounding errors).
		double epsilon = 10e-6;

		Vector2D p = createVector(l1.getPoints().get(0));
		Vector2D r = createVector(l1.getPoints().get(1));
		r = r.subtract(p);

		Vector2D q = createVector(l2.getPoints().get(0));
		Vector2D s = createVector(l2.getPoints().get(1));
		s = s.subtract(q);

		double rCrossS = cross(r, s);

		if (rCrossS <= epsilon && rCrossS >= -1 * epsilon) {
			// lines are parallel, but don't intersect.
			return false;
		}

		// TODO
		return false;
	}

	/**
	 * @see http://paulbourke.net/geometry/sphereline/
	 */
	public boolean lineIntersectsCircle(MLine li, MCircle ci) {
		// TODO
		return false;
	}

	private static Vector2D createVector(MPoint p) {
		return new Vector2D(p.getX(), p.getY());
	}

	private static double square(double d) {
		return d * d;
	}

	/** http://paulbourke.net/geometry/2circle/ */
	public boolean circleIntersectsCircle(MCircle c1, MCircle c2) {
		// TODO
		return false;
	}
}
