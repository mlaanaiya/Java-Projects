package menu;

import java.util.Stack;
import menu.commande.*;

/**
  * Historique permet de gérer des commandes réversibles, en particulier les
  * annuler et les refaire.
  *
  * @author	Xavier Crégut
  * @version	1.2
  */

public class Historique {

	/** les commandes réversibles exécutées. */
	private Stack<CommandeReversible> cmd_executees;

	/** les commandes annulées */
	private Stack<CommandeReversible> cmd_annulees;

	/** Initialiser l'historique comme vide. */
	public Historique() {
		cmd_executees = new Stack<CommandeReversible>();
		cmd_annulees = new Stack<CommandeReversible>();
	}

	/** Le nombre de commandes exécutées (et non annulées) */
	public int getNbCommandesExecutees() {
		return cmd_executees.size();
	}

	/** Le nombre de commandes annulées (et qui peuvent donc être rejouées). */
	public int getNbCommandesAnnulees() {
		return cmd_annulees.size();
	}

	/** Supprimer toutes les commandes enregistrer. */
	public void vider() {
		cmd_executees.clear();
		cmd_annulees.clear();
	}

	/** Enregistrer la commande cmd comme étant la dernière commande exécutée.
	  */
	public void enregistrer(CommandeReversible cmd) {
		cmd_annulees.clear();
		cmd_executees.push(cmd);
	}

	/** Annuler une commande. */
	public void annuler() {
		CommandeReversible cmd = cmd_executees.pop();
		cmd.annuler();
		cmd_annulees.push(cmd);
	}

	/** Rejouer la dernière commande annulée. */
	public void refaire() {
		CommandeReversible cmd =  cmd_annulees.pop();
		cmd.refaire();
		cmd_executees.push(cmd);
	}

}
