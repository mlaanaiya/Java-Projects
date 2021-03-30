package allumettes;

/**
 * La classe Joueur modélise un joueur.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class Joueur {
	/** Nom du joueur.*/
	private String nom;
	/** Strategie adoptée par le joueur.*/
	private Strategie strategie;

	/** Construire le joueur à partir de son nom
	 * et sa stratégie.
	 * @param nom du joueur
	 * @param strategie du joueur
	 */
	public Joueur(String nom, Strategie strategie) {
		this.nom = nom;
		this.strategie = strategie;
	}

	/** Avoir le nom du joueur.
	  * @return nom du joueur
	  */
	public String getNom() {
		return this.nom;
	}

	/** Changer le nom du joueur.
	 * @param nom nouveau nom
	 */
	public void setNom(String nom) {
	        assert (nom != null && nom.length() > 0);
	        this.nom = nom;
	}

	/** Avoir la stratégie du joueur.
	  * @return strategie du joueur
	  */
	public Strategie getStrategie() {
		return this.strategie;
	}

	/** Changer la stratégie du joueur.
	 * @param strategie nouvelle stratégie
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	/** Avoir le nombre de prises.
	 * @param jeu
	 * @return nombre de prises
	 */
	public int getPrise(Jeu jeu) throws CoupInvalideException {
		return this.strategie.prise(jeu, this);

	}

}

