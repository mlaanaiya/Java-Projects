package editeur.commande;

import editeur.Ligne;
import menu.commande.CommandeReversible;

/** Mettre le curseur en première position.
 * @author	Xavier Crégut
 * @version	1.4
 */
public class CommandeCurseurRAZ extends CommandeLigne {

	/** Position du curseur avant de faire RAZ. */
	private int curseurAvant;

	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeCurseurRAZ(Ligne l) {
		super(l);
	}

	public CommandeReversible copie() {
		return new CommandeCurseurRAZ(ligne);
	}

	protected void memoriser() {
		curseurAvant = ligne.getCurseur();
	}

	public void refaire() {
		ligne.raz();
	}

	public void annuler() {
		for (int i = 1; i < curseurAvant; i++) {
			ligne.avancer();
		}
		// Remarque : Il serait plus judicieux d'ajouter une
		// méthode sur la ligne pour positionner le curseur.
	}

	public boolean estExecutable() {
		return ligne.getLongueur() > 0;
	}

}
