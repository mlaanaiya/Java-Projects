package menu.commande;

import menu.*;

/** La commande qui consiste à refaire une commande.
  * @author	Xavier Crégut
  * @version	1.2
  */

public class CommandeRefaire
	extends CommandeHistorique
{

	public CommandeRefaire(Historique h) {
		super(h);
	}

	public boolean estExecutable() {
		return memoire.getNbCommandesAnnulees() > 0;
	}

	public void executer() {
		memoire.refaire();
	}

}
