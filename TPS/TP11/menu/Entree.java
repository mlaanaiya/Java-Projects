package menu;

// À NOTER : La classe n'est pas déclarée « public » car elle n'a
// pas à être utilisée à l'extérieur du paquetage.  Plus
// précisément, elle n'est utilisée que par la classe Menu.  À ce
// titre, il aurait été possible d'en faire une classe interne à
// la classe Menu.  Elle aurait alors été déclarée << static >>.

/** Définition d'une entrée de Menu.
 * @author	Xavier Crégut
 * @version	1.6
 */
class Entree {

	/** L'intitulé de l'entrée. */
	private String intitule;

	/** La commande associée à l'entrée. */
	private Commande commande;

	/** Raccourci pour accéder à la commande.
	 */
	private String raccourci;

	/** Construire une entrée à partir d'un intitulé et d'une commande.
	 * Le raccourci permet de sélectionner directement la commande.
	 * @param unIntitule l'intitulé de l'entrée
	 * @param uneCommande l'intitulé de la commande
	 * @param leRaccourci le raccourci pour sélectionner l'entrée
	 */
	//@ requires uneCommande != null;	// la commande existe
	//@ requires unIntitule != null && unIntitule.length() > 0;
	//@					// l'intitulé existe
	//@ ensures getIntitule() == unIntitule;
	//@ ensures getCommande() == uneCommande;
	public Entree(String unIntitule, Commande uneCommande, String leRaccourci) {
		intitule = unIntitule;
		commande = uneCommande;
		raccourci = leRaccourci;
	}

	/** Construire une entrée à partir d'un intitulé et d'une commande.
	 * @param unIntitule l'intitulé de l'entrée
	 * @param uneCommande l'intitulé de la commande
	 */
	//@ requires uneCommande != null;	// la commande existe
	//@ requires unIntitule != null && unIntitule.length() > 0;
	//@					// l'intitulé existe
	//@ ensures getIntitule() == unIntitule;
	//@ ensures getCommande() == uneCommande;
	public Entree(String unIntitule, Commande uneCommande) {
		this(unIntitule, uneCommande, (String) null);
	}

	/** L'intitulé de l'entrée. */
	public /*@ pure @*/ String getIntitule() {
		return intitule;
	}

	/** La commande associée à l'entrée. */
	public /*@ pure @*/ Commande getCommande() {
		return commande;
	}

	/** Savoir si on a un raccourci.
	 * @param r le raccourci supposé
	 * @return vrai si r est un raccourci de cette entrée
	 */
	public boolean estRaccourci(String r) {
		return this.possedeRaccourci() && r.equals(this.raccourci);
	}

	/** Savoir si l'entrée possède un raccourci. */
	public boolean possedeRaccourci() {
		return this.raccourci != null;
	}

	/** Obtenir le raccourci. */
	public String getRaccourci() { /* FIXME : Remplacer par getLongueurRaccourci */
		return this.raccourci;
	}

	/** Afficher l'entrée.
	 * @param numero le numéro de l'entrée dans le menu
	 * @param lgMax nombre de positions pour afficher l'intitulé
	 * @param lgR nombre de positions pour afficher la raccourci
	 */
	public /*@ pure @*/ void afficher(int numero, int lgMax, int lgR) {
		if (commande.estExecutable()) {
			String num = "" + numero;
			if (num.length() < 2) {
				System.out.print(" ");
			}
			System.out.print(num);
		} else {
			System.out.print(" -");
		}
		System.out.print(") ");
		System.out.print(getIntitule());
		if (possedeRaccourci()) {
			Outils.print(" ",
					lgMax - this.getIntitule().length()		// intitulé
					+ lgR - this.getRaccourci().length());	// raccourci
			System.out.print(" ");
			System.out.print("[" + this.getRaccourci() + "]");
		}
		System.out.println();
	}

}
