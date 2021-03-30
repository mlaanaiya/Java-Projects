
import java.awt.Color;

/** AfficheurTexte fournit un afficheur en texte lisible (et explicite).
  * @author	Xavier Crégut
  * @version	1.5
  */
public class AfficheurTexte implements afficheur.Afficheur {

	public void dessinerPoint(double x, double y, Color c) {
		System.out.println("Point {");
		System.out.println("\tx = " + x);
		System.out.println("\ty = " + y);
		System.out.println("\tcouleur = " + c);
		System.out.println("}");
	}

	public void dessinerLigne(double x1, double y1, double x2, double y2, Color c) {
		System.out.println("Ligne {");
		System.out.println("\tx1 = " + x1);
		System.out.println("\ty1 = " + y1);
		System.out.println("\tx2 = " + x2);
		System.out.println("\ty2 = " + y2);
		System.out.println("\tcouleur = " + c);
		System.out.println("}");
	}

	public void dessinerCercle(double x, double y, double rayon, Color c) {
		System.out.println("Cercle {");
		System.out.println("\tcentre_x = " + x);
		System.out.println("\tcentre_y = " + y);
		System.out.println("\trayon = " + rayon);
		System.out.println("\tcouleur = " + c);
		System.out.println("}");
	}

	public void dessinerTexte(double x, double y, String texte, Color c) {
		System.out.println("Texte {");
		System.out.println("\tx = " + x);
		System.out.println("\ty = " + y);
		System.out.println("\tvaleur = \"" + texte + '"');
		System.out.println("\tcouleur = " + c);
		System.out.println("}");
	}

}
