package allumettes;
import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * Une stratègie qui modélise un joueur humain.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class StrategieHumain implements Strategie {

    	/** Le Scanner pour lire le nombre d'allumettes.*/
	private Scanner scan;

    	/**
     	* Constructeur de la classe Strategie_Humain.
     	*/
    	public StrategieHumain() {
        	this.scan = Strategie.SCANNER;
    	}

	@Override
	public String getNom() {
		return "humain";
	}


	@Override
	public int prise(Jeu jeu, Joueur j) {
	        assert (jeu != null && j != null);
		int n = 0;
		try {
			System.out.print(j.getNom() + ", combien d'allumettes ? ");
           		n = this.scan.nextInt();
		} catch (InputMismatchException e) {
			String s = this.scan.next();
			if (s.equals("triche")) {
				System.out.print("[Une allumette en moins, plus que ");
				System.out.print(jeu.getNombreAllumettes() - 1);
				System.out.println(". Chut !]" + "\t");
				try {
           				jeu.retirer(1);
				} catch (CoupInvalideException f) {
					System.out.println("Qu'est-ce-que je fais ici?");
				}

			} else {
				System.out.println("Vous devez donner un entier.");
			}
			return prise(jeu, j);
		}

		return n;
	 }

}

