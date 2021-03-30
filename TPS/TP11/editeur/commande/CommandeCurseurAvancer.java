package editeur.commande;

import editeur.Ligne;
import menu.commande.CommandeReversible;

/** Avancer le curseur d'une position.
 * @author	Xavier Crégut
 * @version	1.4
 */
public class CommandeCurseurAvancer
	extends CommandeLigne
{

	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeCurseurAvancer(Ligne l) {
		super(l);
	}

	protected void memoriser() {
		// Rien à faire
	}

	public void refaire() {
		ligne.avancer();
	}

	public void annuler() {
		ligne.reculer();
	}

	public boolean estExecutable() {
		return ligne.getCurseur() < ligne.getLongueur();
	}

}
