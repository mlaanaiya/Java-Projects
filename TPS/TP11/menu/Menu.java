package menu;

import java.util.*;
import util.Console;
import menu.commande.*;

/** Définition de menus textuels avec les entrées non
  * sélectionnables désactivées.
  * @author	Xavier Crégut (cregut@enseeiht.fr)
  * @version	1.9
  */
public class Menu implements CommandeNeutre {

	//@ public invariant 0 <= getNbEntrees();

	//@ private invariant titre != null;
	//@ private invariant selection != CMD_QUITTER && selection != null ==>
	//@ 	(\exists int i; i >= -getNbEntreesPredefinies() && i <= getNbEntrees();
	//@			selection == getEntree(i).getCommande());
	//@ private invariant estQuitte() ==> selection == CMD_QUITTER;
	//@ private invariant getNbEntrees() == entrees.size();
	//@ private invariant entreeQuitter != null;
	//@ private invariant getNbEntreesPredefinies() == entreesPredefinies.size();

	private String titre;	// Le titre
	private List<Entree> entrees;	// Les entrées du menu
	static final private Commande CMD_QUITTER = new CommandeNOP();
	static final private Entree entreeQuitter = new Entree("Quitter", CMD_QUITTER);
	private List<Entree> entreesPredefinies;	// Les entrées prédéfinies
	private Commande selection;	// Commande sélectionnée
	private boolean estQuitte;	// le menu a-t-il  été quitté ?

	public enum Mode { REMANANT, VOLATIL };
	private Mode mode;

	private Commande preambule;	// commande exécutée avant d'afficher le menu

	private Historique historique; // pour mémoriser les commandes réversibles

	/** Construire un menu vide (sans entrées).
	 * @param sonTitre le titre du menu
	 * @param unHistorique l'historique à utiliser
	 * @param lePreambule le préambule à utiliser
	 */
	//@ requires sonTitre != null;	// le titre existe
	//@ requires lePreambule != null;	// le préambule est défini
	//@ requires unHistorique != null;	// l'historique est défini
	//@ ensures getMode() == Mode.REMANANT;	// menu rémanant
	//@ ensures getNbEntrees() == 0;	// le menu est vide
	//@ ensures estQuitte() == false;	// pas encore quitter !
	public Menu(String sonTitre, Historique unHistorique, Commande lePreambule) {
		this.entrees = new ArrayList<Entree>();
		this.titre = sonTitre;
		this.selection = null;
		this.estQuitte = false;
		this.preambule = lePreambule;
		this.mode = Mode.REMANANT;
		this.historique = unHistorique;
		this.entreesPredefinies = new ArrayList<Entree>();

		// Ajouter les commandes prédéfinies
		this.ajouterPredefinie("Annuler la dernière opération",
			new CommandeAnnuler(unHistorique), "-");
		this.ajouterPredefinie("Refaire la dernière opération annulée",
			new CommandeRefaire(unHistorique), "+");
	}

	/** Construire un menu vide (sans entrées).  Le préambule ne fait rien.
	 * @param titre le titre du menu
	 */
	//@ requires sonTitre != null;	// le titre existe
	//@ requires unHistorique != null;	// l'historique est défini
	//@ ensures getNbEntrees() == 0;	// le menu est vide
	//@ ensures estQuitte() == false;	// pas encore quitter !
	//@ ensures getMode() == Mode.REMANANT;	// menu rémanant
	public Menu(String sonTitre, Historique unHistorique) {
		this(sonTitre, unHistorique, null);
	}

	/** le nombre d'entrées du menu.  */
	public /*@ pure @*/ int getNbEntrees() {
		return this.entrees.size();
	}

	/** le nombre d'entrées prédéfinies du menu.  */
	public /*@ pure @*/ int getNbEntreesPredefinies() {
		return this.entreesPredefinies.size();
	}

	/** mode du menu. */
	public /*@ pure @*/ Mode getMode() {
		return this.mode;
	}

	/** Rendre le menu rémanant. */
	//@ ensures getMode() == Mode.REMANANT;
	public void setRemanant() {
		this.mode = Mode.REMANANT;
	}

	/** Rendre le menu volatil. */
	//@ ensures getMode() == Mode.VOLATIL;
	public void setVolatil() {
		this.mode = Mode.VOLATIL;
	}

	/** Obtenir une entrée du menu.
	  * @param i position de l'entrée
	  * @retrun l'entrée correspondant à i
	  */
	//@ requires  -getNbEntreesPredefinies() <= i && i <= getNbEntrees();
	private /*@ pure @*/ Entree getEntree(int i) {
		if (i > 0) {
			return this.entrees.get(i-1);
		} else if (i < 0) {
			return this.entreesPredefinies.get(-i-1);
		} else {
			return entreeQuitter;
		}
	}

	public /*@ pure @*/ boolean estQuitte() {
		return this.estQuitte;
	}

	/** Ajouter une entrée dans le menu.
	 * @param texte l'intitulé dans le menu
	 * @param cmd la commande associée
	 * @param raccourci la touche de raccourci
	 */
	//@ requires texte != null && texte.length() > 0; // texte défini
	//@ requires cmd != null;		// commande définie
	//@ requires raccourci != null && raccourci.length() > 0 ==>
	//@			!estRaccourciUtilise(raccourci);
	// @ ensures getEntree(getNbEntrees()).getIntitule().equals(texte);
	// @ ensures getEntree(getNbEntrees()).getCommande() == cmd;
	public void ajouter(String texte, Commande cmd, String raccourci) {
		this.entrees.add(new Entree(texte, cmd, raccourci));
	}

	/** Ajouter une entrée dans le menu.
	 * @param texte l'intitulé dans le menu
	 * @param cmd la commande associée
	 */
	//@ requires texte != null && texte.length() > 0; // texte défini
	//@ requires cmd != null;		// commande définie
	public void ajouter(String texte, Commande cmd) {
		this.entrees.add(new Entree(texte, cmd));
	}

	/** Ajouter une entrée prédéfinie dans le menu avec cmd comme
	  * commande associée.  Cette commande est placée après
	  * quitter et est numéroté négativement.
	 */
	//@ requires texte != null && texte.length() > 0;	// texte défini
	//@ requires cmd != null;		// commande définie
	public void ajouterPredefinie(String texte, Commande cmd, String raccourci) {
		this.entreesPredefinies.add(new Entree(texte, cmd, raccourci));
	}

	/** Obtenir la longueur du plus grand intitulé. */
	private /*@ pure @*/ int getLgMaxIntitule() {
		int resultat = 0;
		for (int i = -this.getNbEntreesPredefinies(); i <= this.getNbEntrees(); i++) {
			int lg = this.getEntree(i).getIntitule().length();
			if (lg > resultat) {
				resultat = lg;
			}
		}
		return resultat;
	}

	/** Obtenir la longueur du plus grand raccourci. */
	private /*@ pure @*/ int getLgMaxRaccourci() {
		int resultat = 0;
		for (int i = -this.getNbEntreesPredefinies(); i <= this.getNbEntrees(); i++) {
			Entree entree = this.getEntree(i);
			if (entree.possedeRaccourci()) {
				int lg = entree.getRaccourci().length();
				if (lg > resultat) {
					resultat = lg;
				}
			}
		}
		return resultat;
	}

	/** Tracer une séparation du menu. */
	private void tracerSeparation() {
		Outils.println("-", this.getLgMaxIntitule() + 4 + this.getLgMaxRaccourci() + 2);
	}

	/** Afficher le menu.  Les numéros des commandes non
	  * exécutables ne sont pas affichés.
	  */
	public /*@ pure @*/ void afficher() {
		// Afficher le titre du menu
		tracerSeparation();
		System.out.println(titre);
		tracerSeparation();

		// Afficher les entrées de l'utilisateur
		int nbPositions = this.getLgMaxIntitule();
		int nbPositionsRaccourci = this.getLgMaxRaccourci();
		for (int i = 1; i <= this.getNbEntrees(); i++) {
			this.getEntree(i).afficher(i, nbPositions, nbPositionsRaccourci);
		}

		// Afficher les entrées prédéfinies
		for (int i = 0; i <= getNbEntreesPredefinies(); i++) {
			this.getEntree(-i).afficher(-i, nbPositions, nbPositionsRaccourci);
		}

		// Dessiner la fin du menu
		tracerSeparation();
	}

	/** Obtenir la commande qui correspond à un raccourci.
	 * Si aucune commande ne correspond, le résultat est null.
	 */
	private /*@ pure @*/ Commande getCommandeMenu(String r) {
		int i = -this.getNbEntreesPredefinies();
		while (i <= this.getNbEntrees() && ! this.getEntree(i).estRaccourci(r)) {
			i++;
		}
		return i <= this.getNbEntrees() ? this.getEntree(i).getCommande() : null;
	}

	/** Savoir si un caractère est déjà utilisé comme raccourci d'une
	 * commande du menu.
	 */
	public /*@ pure @*/ boolean estRaccourciUtilise(String r) {
		return this.getCommandeMenu(r) != null;
	}

	/** Déterminer la sélection correspondant à un ordre de
	 * l'utilisateur saisi au clavier.
	 * Si la sélection est invalide, une nouvelle saisie demandée
	 * à l'utilisateur.
	 */
	public /*@ pure @*/ Commande selectionUtilisateur() {
		boolean saisieOK = false;
		Commande resultat = null;
		int choix = 0;	// XXX : Pourrait être supprimé => local de do { !
		do {
			String ordre = null;
			try {
				ordre = Console.readLine("Votre choix : ");
				choix = Integer.parseInt(ordre);

				saisieOK = -this.getNbEntreesPredefinies() <= choix
							&& choix <= this.getNbEntrees();
				if (!saisieOK) {
					System.out.println("Le numéro doit être compris entre "
							+ -this.getNbEntreesPredefinies() + " et "
							+ this.getNbEntrees() + " !");
				}
				else {
					resultat = this.getEntree(choix).getCommande();
				}
			}
			catch (NumberFormatException e) {
				// La chaîne n'était pas un entier, il faut donc la décoder...
				if (ordre == null || ordre.length() == 0) {
					System.out.println("Donnez un numéro ou un raccourci !");
				} else {
					// Chercher si l'ordre est un raccourci
					resultat = this.getCommandeMenu(ordre);
					saisieOK = resultat != null;
					if (!saisieOK) {
						System.out.println("Le raccourci ne correspond "
								+ "à aucune commande !");
					}
				}
			}
		} while (!saisieOK);
		return resultat;
	}

	/** Saisir le choix de l'utilisateur.  Le choix correspond à
	 * une entrée du menu (y compris 0 pour quitter).
	 * L'utilisateur peut sélectionner une entrée dont la
	 * commande associée n'est pas exécutable.
	 */
	//@ ensures ! estQuitte();
	public void selectionner() {
		// Choisir une entrée
		this.estQuitte = false;
		boolean choix_valide = false;
		do {
			this.selection = this.selectionUtilisateur();
			choix_valide = this.selection != null;
			if (!choix_valide) {
				System.out.println("Le numéro doit être compris entre "
						+ -this.getNbEntreesPredefinies() + " et "
						+ this.getNbEntrees() + " !");
			}
		} while (!choix_valide);
	}

	/** Valider la sélection.  Ceci consiste à exécuter la
	 * commande associée à l'entrée sélectionnée.  Si l'entrée
	 * sélectionnée est non exécutable, un message d'erreur est
	 * signalé.
	 */
	public void valider() {
		if (this.selection == CMD_QUITTER) {
			this.estQuitte = true;
		} else {
			if (this.selection.estExecutable ()) {
				if (this.selection instanceof CommandeReversible) {
					// Ajouter la commande dans l'historique
					CommandeReversible nouvelle
							= ((CommandeReversible) this.selection).copie();
					nouvelle.executer();
					this.historique.enregistrer(nouvelle);
				} else {
					if (this.selection instanceof CommandeIrreversible) {
						// Supprimer toutes les commandes mémorisées
						this.historique.vider();
					}
					this.selection.executer();
				}
			} else {
				System.out.println("Opération non réalisable !");
			}
		}
	}

	/** Gérer le menu.  Ceci consiste à afficher le menu, sélectionner
	  * le choix de l'utilisateur puis valider ce choix.
	  * Le comportement de gerer() peut être adapté grâce à la
	  * commande passée en paramètre du constructeur.
	  *
	  * En fonction du mode du menu, le menu peut être rémanant (il
	  * reste tant que l'utilisateur ne quitte pas) ou << volatil >>
	  * (il disparaît après la première sélection valide).
	  */
	public void gerer() {
		do {
			// Exécuter le préambule
			if (this.preambule != null && this.preambule.estExecutable()) {
				this.preambule.executer();
			}

			// Afficher le menu
			this.afficher();

			// Sélectionner une entrée dans le menu
			this.selectionner();

			// Valider l'entrée sélectionnée
			this.valider();
		} while (this.mode == Mode.REMANANT && !this.estQuitte());
	}

	/** Un menu est exécutable dès que l'une de ses commandes l'est. */
	public boolean estExecutable() {
		boolean resultat = false;
		Iterator<Entree> it = this.entrees.iterator();
		while (!resultat && it.hasNext()) {
			Entree e = it.next();
			resultat = e.getCommande().estExecutable();
		}
		return resultat;
	}

	public void executer() {
		this.gerer();
	}

}
