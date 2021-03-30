package allumettes;

/**
 * La classe StrategieRapide permet de définir une prise maximale.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class StrategieRapide implements Strategie {

	@Override
	public int prise(Jeu jeu, Joueur j) {
		assert (jeu != null && j != null);
		return Math.min(Jeu.PRISE_MAX, jeu.getNombreAllumettes());
	}

	@Override
	public String getNom() {
		return "rapide";
	}
}

