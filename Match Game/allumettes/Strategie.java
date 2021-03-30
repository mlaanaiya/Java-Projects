package allumettes;
import java.util.Scanner;
/**
 * L'interface Strategie permet de définir
 * la stratégie adoptée par le joueur.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public interface Strategie {
	/** Le seul scanner qu'on va utiliser.*/
    	Scanner SCANNER = new Scanner(System.in);
	/** Retourner le nombre de prises.
	 * @param jeu en cours
	 * @param j joueur
	 * @return nombre de prises
	 * @throws CoupInvalideException tentative de prendre un nombre invalide d'alumettes
	 */
	int prise(Jeu jeu, Joueur j) throws CoupInvalideException;
	/** Avoir le nom de la stratégie.
	 * @return nom de la stratégie
	 */
	String getNom();
}

