package allumettes;
import java.util.Random;

/**
 * La classe StrategieNaive définit un joueur qui prend aleatoirement sa prise.
 * @author	Laanaiya Mahmoud
 * @version 	2.0
 */

public class StrategieNaive implements Strategie {

	@Override
	public String getNom() {
		return "naif";
	}

	@Override
	public int prise(Jeu jeu, Joueur j) {
		assert (jeu != null && j != null);
	        Random r = new Random();
	        if (jeu.getNombreAllumettes() > Jeu.PRISE_MAX) {
	            return r.nextInt(Jeu.PRISE_MAX) + 1;
	        } else {
	            return r.nextInt(jeu.getNombreAllumettes()) + 1;
	        }
	}
}
