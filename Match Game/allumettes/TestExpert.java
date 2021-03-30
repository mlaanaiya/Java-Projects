package allumettes;
import org.junit.*;
import static org.junit.Assert.*;
/**
 * Classe de test de la classe StrategieExpert.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class TestExpert {

	/** Argument pour l'exécution en JUnit sur le terminal.
	 * @param args argument
	 */
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("allumettes.TestExpert");
        }
	/** Stratégie à tester.*/
    	private Strategie strategie;
	/** Joueur à tester.*/
	private Joueur j;

	/** Construction du joueur.*/
    	@Before
    	public void setUp() {
        	this.strategie = new StrategieExpert();
		j = new Joueur("Mahmoud", strategie);
    	}

	/** Le test de la stratégie experte.*/
	@Test
	public void testerExpert() throws CoupInvalideException {
		int a = Jeu.PRISE_MAX * Jeu.PRISE_MAX;
		int b = Jouer.NBALLUMETTES;
		int c = Jeu.PRISE_MAX;
		// Une fois au dessous de 5, on prend un nombre de telle sorte
		// de laisser une seule allumette.
		for (int i = c - 1; i <= c + 1; i++) {
            		assertEquals(i - 1,
			 this.strategie.prise(new JeuAllumettes(i), j));
		}
		// Une fois arrivé au dessous de 9, on cherche à laisser que 5
		// allumettes dans le jeu.
		for (int i = 2 * c; i <= a - 1; i++) {
			assertEquals(i - Jeu.PRISE_MAX - 2,
			 this.strategie.prise(new JeuAllumettes(i), j));
		}

		// On cherche à avoir 9 allumettes.
		for (int i = b - c; i <= b - 1; i++) {
			assertEquals(i - a,
			this.strategie.prise(new JeuAllumettes(i), j));
		}
		assertEquals(1, this.strategie.prise(new JeuAllumettes(1), j));
    	}
}

