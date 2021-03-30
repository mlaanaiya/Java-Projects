package editeur.commande;

import editeur.Ligne;
import menu.commande.CommandeReversible;

/** Supprimer le caractère sous le curseur.
  * @author	Xavier Crégut
  * @version	$Revision: 1.5 $
  */
public class CommandeCurseurSupprimer extends CommandeLigne
{

	private char caractereSupprime;
	private boolean curseurDeplace;

	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeCurseurSupprimer(Ligne l) {
		super(l);
	}

	public CommandeReversible copie() {
		return new CommandeCurseurSupprimer(ligne);
	}

	protected void memoriser() {
		caractereSupprime = ligne.getCourant();
		curseurDeplace = ligne.getCurseur() == ligne.getLongueur();
	}

	public void refaire() {
		 ligne.supprimer();
	}

	public void annuler() {
		if (curseurDeplace) {
			if (ligne.getLongueur() > 0) {
				ligne.ajouterApres(caractereSupprime);
				ligne.avancer();
			} else {
				ligne.ajouterFin(caractereSupprime);
			}
		} else {
			ligne.ajouterAvant(caractereSupprime);
			ligne.reculer();
		}
	}

	public boolean estExecutable() {
		return ligne.getLongueur() > 0;
	}

}
