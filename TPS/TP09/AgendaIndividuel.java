/**
 * Définition d'un agenda individuel.
 */
public class AgendaIndividuel extends AgendaAbstrait {

	private String[] rendezVous;	// le texte des rendezVous


	/**
	 * Créer un agenda vide (avec aucun rendez-vous).
	 *
	 * @param nom le nom de l'agenda
	 * @throws IllegalArgumentException si nom nul ou vide
	 */
	public AgendaIndividuel(String nom) {
		super(nom);
		this.rendezVous = new String[Agenda.CRENEAU_MAX + 1];
			// On gaspille une case (la première qui ne sera jamais utilisée)
			// mais on évite de nombreux « creneau - 1 »
	}


	@Override
	public void enregistrer(int creneau, String rdv) throws OccupeException {
		verifierChaineNonVide(rdv);
		verifierLibre(creneau);

		this.rendezVous[creneau] = rdv;
	}


	@Override
	public boolean annuler(int creneau) {
		verifierCreneauValide(creneau);

		boolean modifie = this.rendezVous[creneau] != null;
		this.rendezVous[creneau] = null;
		return modifie;
	}


	@Override
	public String getRendezVous(int creneau) throws LibreException {
		verifierOccupe(creneau);

		return this.rendezVous[creneau];
	}


	/**
	 * Vérifier que le creneau est libre.
	 * Lève l'exception OccupeException si ce n'est pas le cas.
	 *
	 * @throws OccupeException si le créneau est occupé
	 */
	protected void verifierLibre(int creneau) throws OccupeException {
		verifierCreneauValide(creneau);
		if (this.rendezVous[creneau] != null) {
			throw new OccupeException(this.getNom() + "@" + creneau
					+ " : " + this.rendezVous[creneau]);
		}
	}


	/**
	 * Vérifier que le creneau est occupé.
	 * Lève l'exception LibreException si ce n'est pas le cas.
	 *
	 * @throws LibreException si le créneau est libre
	 */
	protected void verifierOccupe(int creneau) throws LibreException {
		verifierCreneauValide(creneau);
		if (this.rendezVous[creneau] == null) {
			throw new LibreException("pour : " + this.getNom() + "@" + creneau);
		}
	}

}
