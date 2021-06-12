package gameObjects;

import java.awt.Image;
import game.VariablesGlobales;
import physique.Position;
import physique.Size;

public abstract class GameObject {
	private VariablesGlobales vg = new VariablesGlobales();
	private int lx = vg.lx;
	private int ly = vg.ly;

	/** Position de l'objet. */
	protected Position position;
	/** Taille de l'objet. */
	protected Size size;

	/** Constructeur du GameObject. */
	public GameObject() {
		position = new Position(lx,ly);
		size = new Size(lx,ly);
	}
	/** Mettre a jour l'eta du GameObject. */
	public abstract void update();

	/** Dessiner le GameObject sur le canvas. */
	public abstract Image getSprite();

	/** Getter de la position de l'objet.
	 * @return position, la position de l'objet.
	 */
	public Position getPosition() {
		return this.position;
	}

	/** Getter de la taille de l'objet.
	 * @return size, la taille de l'objet.
	 */
	public Size getSize() {
		return this.size;
	}
}
