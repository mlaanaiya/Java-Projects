package allumettes;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Random;
/**
 * Classe de test de la classe StrategieRapide.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class TestRapide {

	/** Argument pour l'exécution en JUnit sur le terminal.
	 * @param args argument
	 */
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("allumettes.TestRapide");
        }
	/** Stratégie à tester.*/
    	private Strategie strategie;
	/** Joueur à tester.*/
	private Joueur j;

	/** Construction du joueur.*/
    	@Before
    	public void setUp() {
        	this.strategie = new StrategieRapide();
		j = new Joueur("Mahmoud", strategie);
    	}

	/** Le test de la stratégie rapide.*/
    	@Test
	public void testerRapide() throws CoupInvalideException {
            	assertEquals(1, this.strategie.prise(new JeuAllumettes(1), j));
		assertEquals(2, this.strategie.prise(new JeuAllumettes(2), j));
		Random r = new Random();
		int randallumettes = r.nextInt(Jouer.NBALLUMETTES - 2) + Jeu.PRISE_MAX;
	       	assertEquals(Jeu.PRISE_MAX, this.strategie.prise(new JeuAllumettes(randallumettes), j));
    	}
}

