package editeur.commande;

import editeur.Ligne;
import util.Console;
import menu.commande.CommandeReversible;

/** Ajouter un caractère à la fin de la ligne.
 * @author	Xavier Crégut
 * @version	1.4
 */
public class CommandeAjouterFin
	extends CommandeLigne
{

	/** Texte qui a été ajouté à la ligne. */
	private String texteAjoute;

	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeAjouterFin(Ligne l) {
		super(l);
	}

	public CommandeReversible copie() {
		return new CommandeAjouterFin(ligne);
	}

	protected void memoriser() {
		texteAjoute = Console.readLine("Texte à insérer : ");
	}

	public void refaire() {
		for (int i = 0; i < texteAjoute.length(); i++) {
			ligne.ajouterFin(texteAjoute.charAt(i));
		}
	}

	public void annuler() {
		for (int i = 0; i < texteAjoute.length(); i++) {
			ligne.supprimerDernier();
		}
	}

	public boolean estExecutable() {
		return true;
	}

}
