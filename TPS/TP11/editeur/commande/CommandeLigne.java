package editeur.commande;

import editeur.Ligne;
import menu.Commande;
import menu.commande.CommandeReversible;


/** Une CommandeLigne est une commande qui travaille sur une
 * ligne de l'éditeur orienté ligne.
 * @author	Xavier Crégut
 * @version	1.4
 */
abstract public class CommandeLigne
	implements CommandeReversible
{
	/** La ligne manipulée par la commande. */
	protected Ligne ligne;

	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeLigne(Ligne l) {
		ligne = l;
	}

	// Nous définissons un comportement par défaut pour copie()
	// qui consiste à renvoyer la commande elle-même.  Ceci
	// fonctionnera pour toutes les commandes qui n'ont pas à
	// mémoriser un état pour être annulées (ou refaites).
	public CommandeReversible copie() {
		return this;
	}

	// executer() consiste à mémoriser les informations
	// nécessaires pour annuler ou refaire(), puis à faire la
	// commande,  donc à la refaire() !
	final public void executer() {
		memoriser();
		refaire();
	}

	/** Mémoriser les informations nécessaires pour annuler ou
	  * refaire la commande.
	  */
	abstract protected void memoriser();

}
