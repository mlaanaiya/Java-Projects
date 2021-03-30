import java.util.*;

/** @has 0..* "" 0..* Agenda */
public class GroupeAgenda extends AgendaAbstrait {

	protected List<Agenda> agendas;
		// Il serait plus logique d'utiliser un ensemble plutôt qu'une liste
		// car il ne faut pas ajotuer deux fois le même agenda dans le même
		// groupe.


	/**
	 * Créer un groupe d'agenda vide (avec aucun rendez-vous).
	 *
	 * @param nom le nom de l'agenda
	 * @throws IllegalArgumentException si nom nul ou vide
	 */
	public GroupeAgenda(String nom) {
		super(nom);
		this.agendas = new ArrayList<>();
	}

	/**
	 * Ajouter un nouvel agenda dans ce groupe.
	 * Attention, il ne faut pas ajouter plusieurs fois le même groupe dans un
	 * même groupe.  Ceci ne peut pas être vérifier avec la modélisation
	 * proposée si on ajoute des dans un sous-groupe un agenda qui existe
	 * déjà dans le super-groupe.
	 *
	 * @param a le nouvel agenda à ajouter
	 *
	 * @throws IllegalArgumentException si a est null ou déjà dans le groupe
	 */
	public void ajouter(Agenda a) {
		verifierNotNull(a);
		if (this.contient(a)) {
			throw new IllegalArgumentException("déjà dans le groupe "
					+ this.getNom() + " : " + a.getNom());
		}
		if (a instanceof GroupeAgenda && ((GroupeAgenda) a).contient(this)) {
			throw new IllegalArgumentException("refus de créer un cycle : "
					+ a.getNom() + " contient " + this.getNom());
		}

		this.agendas.add(a);
	}

	/** Savoir si un agenda appartient à ce groupe.
	 * @param cherche l'agenda cherché
	 * @return vrai si l'agenda appartient à ce groupe
	 */
	public boolean contient(Agenda cherche) {
		for (Agenda a : this.agendas) {
			if (a.equals(cherche)) {
				return true;
			} else if (a instanceof GroupeAgenda) {
				GroupeAgenda sousGroupe = (GroupeAgenda) a;
				if (sousGroupe.contient(cherche)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Le rendez-vous est soit enregistré dans tous les agendas du groupe,
	 * soit dans aucun.
	 */
	@Override
	public void enregistrer(int creneau, String rdv) throws OccupeException {
		verifierCreneauValide(creneau);
		verifierNotNull(rdv);
		verifierChaineNonVide(rdv);

		List<Agenda> agendasModifies = new ArrayList<>();
		try {
			// Enregistrer dans les différents agendas
			for (Agenda a : this.agendas) {
				a.enregistrer(creneau, rdv);
				agendasModifies.add(a);
			}
		} catch (OccupeException e) {
			// Supprimer les enregisrements faits
			for (Agenda a : agendasModifies) {
				a.annuler(creneau);
			}
			throw e;
				// On relève la même exception, donc l'exception de l'agenda
				// individuel qui était occupé !
		}
	}

	@Override
	public boolean annuler(int creneau) {
		verifierCreneauValide(creneau);

		boolean modifie = false;
		for (Agenda a : this.agendas) {
			modifie = a.annuler(creneau) || modifie;	// Attention à l'ordre !
		}
		return modifie;
	}

	@Override
	public String getRendezVous(int creneau) throws LibreException {
		verifierCreneauValide(creneau);

		String rdvCommun = null;
		int nbLibres = 0;
		for (Agenda a : this.agendas) {
			try {
				String rdv = a.getRendezVous(creneau);
				if (rdv == null) {
					// C'est donc que a est un groupe sans rendez-vous commun
					return null;
				} else {
					if (rdvCommun == null) {
						rdvCommun = rdv;
					} else if (! rdv.equals(rdvCommun)) {
						// Deux rendez-vous différents !
						return null;
					} else {
					}
				}
			} catch (LibreException e) {
				nbLibres++;
			}
		}
		if (nbLibres == agendas.size()) {
			throw new LibreException();
		} else if (nbLibres > 0) {
			return null;
		} else {
			return rdvCommun;
		}
	}

	/**
	 * Proposer un rendez-vous à tous les agendas de ce groupe.
	 * Le rendez-vous n'est enregistré que dans les agendas qui sont libres
	 * pour le créneau considéré.  Il est ignoré par les autres.
	 *
	 * @param creneau le créneau du rendez-vous
	 * @param rdv le rendez-vous
	 * @throws CreneauInvalideException si le créneau est invalide
	 * @throws IllegalArgumentException si nom nul ou vide
	 */
	public void proposer(int creneau, String rdv) {
		verifierCreneauValide(creneau);
		verifierNotNull(rdv);
		verifierChaineNonVide(rdv);

		for (Agenda a : this.agendas) {
			if (a instanceof GroupeAgenda) {
				GroupeAgenda g = (GroupeAgenda) a;
				g.proposer(creneau, rdv);
			} else {
				try {
					a.enregistrer(creneau, rdv);
				} catch (OccupeException e) {
					// Rien à faire
					assert ! (a instanceof GroupeAgenda);
				}
			}
		}
	}
}
