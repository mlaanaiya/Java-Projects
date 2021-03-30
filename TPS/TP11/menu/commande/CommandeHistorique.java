package menu.commande;

import menu.*;

/** CommandeHistorique permet de factoriser les caractéristiques
  * communes aux commandes qui manipulent un historique.  Ce sont
  * des commandes qui sont considérées comme neutres par rapport
  * au modèle.
  * @author	Xavier Crégut
  * @version	1.1
  */
abstract public class CommandeHistorique
		implements CommandeNeutre
{
	protected Historique memoire;

	//@ requires h != null;
	public CommandeHistorique(Historique h) {
		memoire = h;
	}

}
