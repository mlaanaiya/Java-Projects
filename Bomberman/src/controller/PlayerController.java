package controller;

import java.awt.event.KeyEvent;

// Classe qui renvoie les entrees clavier de l'utilisateur
public class PlayerController implements Controller {
	
	/** L'entree de l'utilisater. */
	private Input input;
	
	/** Constructeur du Controleur du joueur,
	 * @param input, entree saisie au clavier.
	 */
	public PlayerController(Input input) {
		this.input = input;
	}

	/** Renvoie si la touche fleche haut a ete / est toujours pressee. */
	@Override
	public boolean isUp() {
		return input.isPressed(KeyEvent.VK_UP); //VK = Virtual Key
	}
	
	/** Renvoie si la touche fleche bas a ete / est toujours pressee. */
	@Override
	public boolean isDown() {
		return input.isPressed(KeyEvent.VK_DOWN);
	}
	
	/** Renvoie si la touche fleche gauche a ete / est toujours pressee. */
	@Override
	public boolean isLeft() {
		return input.isPressed(KeyEvent.VK_LEFT);
	}
	
	/** Renvoie si la touche fleche droite a ete / est toujours pressee. */
	@Override
	public boolean isRight() {
		return input.isPressed(KeyEvent.VK_RIGHT);
	}
	

	/** Renvoie si la touche espace a ete / est toujours pressee. */
	@Override
	public boolean dropBomb() {
		// TODO Auto-generated method stub
		return input.isPressed(KeyEvent.VK_SPACE);
	}
	
}
