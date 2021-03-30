package menu.commande;

import menu.*;

/** Annuler la dernière opération de l'historique.
  * @author	Xavier Crégut
  * @version	1.2
  */

public class CommandeAnnuler extends CommandeHistorique {

	public CommandeAnnuler(Historique h) {
		super(h);
	}

	public boolean estExecutable() {
		return memoire.getNbCommandesExecutees() > 0;
	}

	public void executer() {
		memoire.annuler();
	}

}
