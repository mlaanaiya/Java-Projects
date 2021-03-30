package allumettes;
import org.junit.*;
import static org.junit.Assert.*;
/**
 * Classe de test de la classe StrategieLente.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class TestLente {

	/** Argument pour l'exécution en JUnit sur le terminal.
	 * @param args argument
	 */
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("allumettes.TestLente");
        }
	/** Stratégie à tester.*/
    	private Strategie strategie;
	/** Joueur à tester.*/
	private Joueur j;

	/** Construction du joueur.*/
    	@Before
    	public void setUp() {
        	this.strategie = new StrategieLente();
		j = new Joueur("Mahmoud", strategie);
    	}

	/** Le test de la stratégie lente.*/
    	@Test
	public void testerLente() throws CoupInvalideException {
		for (int i = 1; i <= 13; i++) {
            		assertEquals(1, this.strategie.prise(new JeuAllumettes(i), j));
		}
    	}
}

