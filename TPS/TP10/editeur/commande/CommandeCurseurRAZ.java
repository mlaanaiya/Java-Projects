package editeur.commande;

import editeur.Ligne;

/** Mettre le curseur en première position.
 * @author	Xavier Crégut
 * @version	1.4
 */
public class CommandeCurseurRAZ extends CommandeLigne {

	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeCurseurRAZ(Ligne l) {
		super(l);
	}

	public void executer() {
		ligne.raz();
	}

	public boolean estExecutable() {
		return ligne.getLongueur() > 0;
	}

}
