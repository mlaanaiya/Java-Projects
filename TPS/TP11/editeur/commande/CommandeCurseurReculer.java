package editeur.commande;

import editeur.Ligne;
import menu.commande.CommandeReversible;

/** Reculer le curseur d'une position.
 * @author	Xavier Crégut
 * @version	1.4
 */
public class CommandeCurseurReculer
	extends CommandeLigne
{

	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeCurseurReculer(Ligne l) {
		super(l);
	}

	protected void memoriser() {
		// Rien à faire
	}

	public void refaire() {
		ligne.reculer();
	}

	public void annuler() {
		ligne.avancer();
	}

	public boolean estExecutable() {
		return ligne.getCurseur() > 1;
	}

}
