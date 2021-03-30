package editeur.commande;

import editeur.Ligne;
import menu.commande.CommandeIrreversible;

/** Commande qui commence l'édition d'une nouvelle ligne.  On
  * considère cette commande comme irréversible.
  *
  * Remarque : dans la version actuelle elle se contente
  * d'effacer tous les caractères de la ligne.
  * @author	Xavier Crégut
  * @version	1.3
  */
public class CommandeNouvelleLigne
	implements CommandeIrreversible
{

	// Remarque : Cette commande n'hérite pas de CommandeLigne
	// car toutes les commandes lignes sont définies comme étant
	// CommandeReversible !

	private Ligne ligne;

	public CommandeNouvelleLigne(Ligne l) {
		ligne = l;
	}

	public boolean estExecutable() {
		return true;
	}

	public void executer() {
		while (ligne.getLongueur() > 0) {
			ligne.supprimer();
		}
	}

}
