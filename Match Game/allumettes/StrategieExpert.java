package allumettes;
import java.util.Random;

/**
 * La classe StrategieExpert essaie de gagner la partie.
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class StrategieExpert implements Strategie {

	@Override
	public String getNom() {
		return "expert";
	}

	@Override
	public int prise(Jeu jeu, Joueur j) {
		assert (jeu != null && j != null);
		Random r = new Random();
		int nbAllumettes = jeu.getNombreAllumettes();
		if ((nbAllumettes) % (Jeu.PRISE_MAX + 1) == 1) {
			return r.nextInt(Math.min(Jeu.PRISE_MAX, nbAllumettes)) + 1;
		} else {
			return (nbAllumettes - 1) % (Jeu.PRISE_MAX + 1);
		}
	}
}

