package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
	/** Un tableau contenant des booleens definit par leurs indices.
	 * Chaque case "i" correspond a si le caractere de code ASCII "i" a ete / est toujours presse.
	 */
	private boolean[] pressed;
	
	/** Constructeur de la saisie utilisateur. */
	public Input() {
		pressed = new boolean[500];
	}

	/** Renvoie si le bouton de code ASCII "keyCode" a ete presse.
	 * @param keyCode code ASCII du bouton presse.
	 * @return true si c'est le cas, false sinon.
	 */
	public boolean isPressed(int keyCode) {
		return pressed[keyCode];
	}
	
	// Renvoie la meme chose que keyPressed.
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** Associe a la case de code ASCII correspondant a la touche "e" la valeur true.
	 * @param e, touche presse.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		pressed[e.getKeyCode()] = true;
	}

	/** Associe a la case de code ASCII correspondant a la touche "e" la valeur false.
	 * @param e, touche relache.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		pressed[e.getKeyCode()] = false;
	}
}
