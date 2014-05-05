package shapes;

import javafx.scene.paint.Color;
import whiteBoard.WhiteBoard;

/**
 * @author Frank Grimm
 */
public abstract class Shape implements Drawable {
	private final WhiteBoard whiteBoard;
	private Object representationOnWhiteBoard;
	private boolean solid = false;
	private Color color;

	/**
	 * @param wb
	 *            Das {@link WhiteBoard}, auf dem das {@link Shape} dargestellt
	 *            werden soll.
	 */
	public Shape(WhiteBoard wb) {
		whiteBoard = wb;
	}

	public synchronized void remove() {
		if (representationOnWhiteBoard == null)
			return;
		getWhiteBoard().removeShape(representationOnWhiteBoard);
		representationOnWhiteBoard = null;
	}

	protected synchronized void setRepresentationOnWhiteBoard(Object r) {
		if (representationOnWhiteBoard == r)
			return;
		else if (representationOnWhiteBoard != null)
			remove();
		representationOnWhiteBoard = r;
	}

	/**
	 * Liefert das {@link WhiteBoard}, auf dem das {@link Shape} dargestellt
	 * wird.
	 */
	protected WhiteBoard getWhiteBoard() {
		return whiteBoard;
	}

	/**
	 * Liefert die Farbe des Objekts.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setzt die Farbe.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Gibt an, ob das Objekt ausgefuellt ist.
	 */
	public boolean getSolid() {
		return solid;
	}

	/**
	 * Legt fest, ob das Objekt ausgefuellt sein soll.
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
}