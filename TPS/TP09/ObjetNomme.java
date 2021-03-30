/**
 * Un objet nommé est un objet qui a un nom.
 */
public abstract class ObjetNomme {

	private String nom;

	/**
	 * Initialiser le nom de l'agenda.
	 *
	 * @param nom le nom de l'agenda
	 * @throws IllegalArgumentException si nom n'a pas au moins un caractère
	 */
	public ObjetNomme(String nom) {
		verifierNotNull(nom);
		verifierChaineNonVide(nom);

		this.nom = nom;
	}


	/**
	 * Obtenir le nom de cet objet.
	 * @return le nom de cet objet
	 */
	public String getNom() {
		return this.nom;
	}


	/**
	 * Vérifier que la chaîne est de longueur strictement positive
	 *
	 * @throws IllegalArgumentException si la chaîne est vide
	 */
	protected void verifierChaineNonVide(String chaine) {
		verifierNotNull(chaine);
		if (chaine.length() == 0) {
			throw new IllegalArgumentException("nom vide");
		}
	}


	/**
	 * Vérifier que la poignée est non nulle.
	 *
	 * @throws IllegalArgumentException si la poignée est nulle
	 */
	protected void verifierNotNull(Object p) {
		if (p == null) {
			throw new IllegalArgumentException("argument is null");
		}
	}

}
