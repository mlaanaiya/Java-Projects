import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
  * Classe de test des éxigences manquées.
  * @author	LAANAIYA Mahmoud
  * @version	$Revision$
  */
public class CercleTest {

	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;
	
	// Argument pour l´exécution en JUnit sur le terminal
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("CercleTest");
        }

	// Les points du sujet
	private Point A, B, C;

	// Les cercles du sujet
	private Cercle C1, C2, C3;

	@Before public void setUp() {
		// Construire les points
		A = new Point(1, 2);
		B = new Point(2, 1);
		C = new Point(4, 1);

		// Construire les cercles
		C1 = new Cercle(A, B);
		C2 = new Cercle(A, C, Color.red);
		C3 = Cercle.creerCercle(B,C);
	}

	/** Vérifier si deux points ont mêmes coordonnées.
	  * @param p1 le premier point
	  * @param p2 le deuxième point
	  */
	static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
	}

   	 @Test public void testerE12C1() {
        	memesCoordonnees("E12 : centre de C1 incorrect", new Point(1.5,1.5), C1.getCentre());
		assertEquals("E12 : Rayon de C1 incorrect", Math.sqrt(2)/2, C1.getRayon(), EPSILON);
		assertEquals("E12 : Couleur de C1 incorrect", Color.blue, C1.getCouleur());
    	}
    
   	@Test public void testerE13C2() {
        	memesCoordonnees("E13 : centre de C2 incorrect", new Point(2.5,1.5), C2.getCentre());
        	assertEquals("E13 : Rayon de C2 incorrect", Math.sqrt(10)/2, C2.getRayon(), EPSILON);
		assertEquals("E13 : Couleur de C2 incorrect", Color.red, C2.getCouleur());
    	}
    	@Test public void testerE14C3() {
        	memesCoordonnees("E14 : centre de C3 incorrect", B, C3.getCentre());
		assertEquals("E14 : Rayon de C3 incorrect", 2.0, C3.getRayon(), EPSILON);
	        assertEquals("E14 : Couleur de C3 incorrect", Color.blue, C3.getCouleur());
 	}
}
