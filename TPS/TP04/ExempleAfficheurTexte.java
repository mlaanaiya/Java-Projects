
import java.awt.Color;

/**
  * Programme de test de AfficheurTexte.
  * @author	Xavier Crégut
  * @version	1.4
  */
class ExempleAfficheurTexte {

	public static void main(String[] args) {
		AfficheurTexte txt = new AfficheurTexte();
		txt.dessinerPoint(1, 2, Color.green);
		txt.dessinerLigne(6, 2, 11, 9, Color.red);
		txt.dessinerCercle(4, 4, 2.5, Color.yellow);
		txt.dessinerTexte(1, -2, "Premier dessin", Color.blue);
	}
}

