package menu.commande;

import menu.*;

/** Une commande réversible est une commande qui peut être
  * annulée.  Elle peut être annulée si elle a été exécutée.
  * Elle peut également être refaite.  Une commande ne peut être
  * exécutée qu'une seule fois sinon l'annulation peut ne pas
  * marcher.  Dans ce cas, il faut utiliser copie() pour obtenir
  * une autre commande.
  * @author	Xavier Crégut
  * @version	1.3
  */
public interface CommandeReversible extends Commande {

	/** Annuler l'effet de la commande. */
	void annuler();

	/** Refaire la commande et redonner le résultat de son
	 * exécution initiale.
	 */
	void refaire();

	/** Obtenir une copie de la commande.  Cette copie possède
	 * ses propres caractéristiques et pourra donc être exécutée
	 * et annulée sans conflit avec les autres instances.
	 * @return une commande du même type.
	 */
	CommandeReversible copie();
}
