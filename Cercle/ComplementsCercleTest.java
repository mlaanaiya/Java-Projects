import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;
/**
  * Compléments des tests manqués.
  * @author	LAANAIYA Mahmoud.
  * @version	$Revision$
  */
public class ComplementsCercleTest {

	// Argument pour l´exécution en JUnit sur le terminal
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("ComplementsCercleTest");
        }

	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

	// Les points du sujet
	private Point A, B, C, D, E;

	// Les cercles du sujet
	private Cercle C1, C2, C3, C4;

	@Before public void setUp() {
		// Construire les points
		A = new Point(1, 2);
		B = new Point(2, 1);
		C = new Point(4, 1);
		D = new Point(8, 1);
		E = new Point(8, 4);
		// Construire les cercles
		C1 = new Cercle(A, 2.5); // C1: centre = (1, 2), rayon = 2.5, couleur = blue
		C2 = new Cercle(B, C); // C2: centre = (3, 1), rayon = 1.0, couleur = blue
		C3 = Cercle.creerCercle(D, E); // C3: centre = (), rayon = 3.0, couleur = blue
		C4 = new Cercle(B, C, Color.red); // C4: centre = (3, 1), rayon = 1.0, couleur = red
	}


	@Test public void testerE5C1() {
	// Test pour un point aléatoire	
		double minC1x = -2.5; // Le plus petit abscisse atteint par le cercle centré au point (0, 0)
		double maxC1x = 2.5; // Le plus grand abscisse atteint par le cercle centré au point (0, 0)
		double randC1x = Math.random()*(maxC1x - minC1x) + minC1x; // Abscisse aléatoire entre minC1x et maxC1x
		double randC1y = Math.random()*(Math.sqrt((2.5*2.5 - Math.pow(randC1x, 2)))) + 2.0; // Ordonnée aléatoire (x^2 + y^2 = r^2)		
		assertTrue (C1.contient(new Point(randC1x + 1.0, randC1y)));
	
	// Test pour un point connu	
		assertTrue (C1.contient(A));
	}


	@Test public void testerE5C2() {
	// Test pour un point aléatoire	
		double minC2x = -1.0; // Le plus petit abscisse atteint par le cercle centré au point (0, 0)
		double maxC2x = 1.0; // Le plus grand abscisse atteint par le cercle centré au point (0, 0)
		double randC2x = Math.random()*(maxC2x - minC2x) + minC2x; // Abscisse aléatoire entre minC2x et maxC2x
		double randC2y = Math.random()*(Math.sqrt((1.0 - Math.pow(randC2x, 2)))) + 1.0; // Ordonnée aléatoire 
		assertTrue (C2.contient(new Point(randC2x + 3.0, randC2y)));
	
	// Test pour un point connu	
		assertTrue (C2.contient(B));
	}

	@Test public void testerE5C3() {
	// Test pour un point aléatoire	
		double minC3x = -2.5; // Le plus petit abscisse atteint par le cercle centré au point (0, 0)
		double maxC3x = 1.5; // Le plus grand abscisse atteint par le cercle centré au point (0, 0)
		double randC3x = Math.random()*(maxC3x - minC3x) + minC3x; // Abscisse aléatoire entre minC3x et maxC3x
		double randC3y = Math.random()*(Math.sqrt((9.0 - Math.pow(randC3x, 2)))) + 1.0; // Ordonnée aléatoire 
		assertTrue (C3.contient(new Point(randC3x + 8.0, randC3y)));
	
	// Test pour un point connu	
		assertTrue (C3.contient(D));
	}


	@Test public void testerE8() {
		assertTrue (C1.getCouleur() == Color.blue);	        
		assertTrue (C1.getCouleur() != null);
		assertTrue (C2.getCouleur() == Color.blue);
	        assertTrue (C2.getCouleur() != null);
		assertTrue (C3.getCouleur() == Color.blue);
	        assertTrue (C3.getCouleur() != null);
		assertTrue (C4.getCouleur() == Color.red);
	        assertTrue (C4.getCouleur() != null);
       }

}
