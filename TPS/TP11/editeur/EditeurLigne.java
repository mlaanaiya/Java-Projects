package editeur;

import editeur.commande.*;
import menu.Menu;
import menu.Commande;
import menu.Historique;

/** Un éditeur pour une ligne de texte.  Les commandes de
 * l'éditeur sont accessibles par un menu.
 *
 * @author	Xavier Crégut
 * @version	1.6
 */
public class EditeurLigne {

	/** La ligne de notre éditeur */
	private Ligne ligne;

	/** Le menu principal de l'éditeur */
	private Menu menuPrincipal;
		// Remarque : Tous les éditeurs ont le même menu mais on
		// ne peut pas en faire un attribut de classe car chaque
		// commande doit manipuler la ligne propre à un éditeur !

	/** Initialiser l'éditeur à partir de la lign à éditer. */
	public EditeurLigne(Ligne l) {
		ligne = l;
		AfficheurLigne afficheurLigne = new AfficheurLigne(l);
		Historique historique = new Historique();

		// Créer le menu du curseur
		Menu menuCurseur = new Menu("Menu curseur", historique, afficheurLigne);
		menuCurseur.ajouter("Mettre le curseur au début de la ligne",
					new CommandeCurseurRAZ(ligne), "^");
		menuCurseur.ajouter("Avancer le curseur d'un caractère",
					new CommandeCurseurAvancer(ligne), "l");
		menuCurseur.ajouter("Reculer le curseur d'un caractère",
					new CommandeCurseurReculer(ligne), "h");
		menuCurseur.ajouter("Supprimer le caractère sous le curseur",
					new CommandeCurseurSupprimer(ligne), "x");

		// Créer le menu principal
		menuPrincipal = new Menu("Menu principal", historique, afficheurLigne);
		menuPrincipal.ajouter("Ajouter un texte en fin de ligne",
					new CommandeAjouterFin(ligne), "A");
		menuPrincipal.ajouter("Opérations liées au curseur", menuCurseur, "!c");
		menuPrincipal.ajouter("Éditer une nouvelle ligne",
					new CommandeNouvelleLigne(ligne), "o");
	}

	public void editer() {
		menuPrincipal.gerer();
	}

}

class AfficheurLigne implements Commande {
	private Ligne l;

	public AfficheurLigne(Ligne l_) {
		l = l_;
	}

	public boolean estExecutable() {
		return true;
	}

	public void executer() {
		// Afficher la ligne
		System.out.println();
		l.afficher();
		System.out.println();
	}

}

