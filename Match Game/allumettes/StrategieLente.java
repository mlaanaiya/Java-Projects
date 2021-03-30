package allumettes;

/**
 * La classe StrategieLente permet de définir une prise minimale.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class StrategieLente implements Strategie {


	@Override
	public String getNom() {
		return "lente";
	}

	@Override
	public int prise(Jeu jeu, Joueur j) {
		assert (jeu != null && j != null);
		return 1;
	}
}


