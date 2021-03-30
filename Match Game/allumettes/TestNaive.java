package allumettes;
import org.junit.*;
import static org.junit.Assert.*;
/**
 * Classe de test de la classe StrategieNaive.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class TestNaive {

	/** Argument pour l'exécution en JUnit sur le terminal.
	 * @param args argument
	 */
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("allumettes.TestNaive");
        }
	/** Stratégie à tester.*/
    	private Strategie strategie;
	/** Joueur à tester.*/
	private Joueur j;

	/** Construction du joueur.*/
    	@Before
    	public void setUp() {
        	this.strategie = new StrategieNaive();
		j = new Joueur("Mahmoud", strategie);
    	}

	/** Le test de la stratégie naive.*/
    	@Test
	public void testerLente() throws CoupInvalideException {
		for (int i = 2; i <= Jouer.NBALLUMETTES; i++) {
            		assertTrue(this.strategie.prise(new JeuAllumettes(i), j) <= Jeu.PRISE_MAX);
			assertTrue(this.strategie.prise(new JeuAllumettes(i), j) >= 1);
		}
		assertTrue(this.strategie.prise(new JeuAllumettes(1), j) == 1);
    	}
}

