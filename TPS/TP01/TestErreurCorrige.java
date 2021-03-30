/** Une erreur à la compilation !
  * Pourquoi ?
  * Parce qu'il n'y avait pas de constructeur sans paramètre dans Point.
  * L'erreur est corrigée !
  * @author	Xavier Crégut
  * @version	1.3
  */
public class TestErreurCorrige {

	/** Méthode principale */
	public static void main(String[] args) {
		Point p1 = new Point(1, 2);
			// Quand on créé un objet, on doit l'initialiser correctement,  avec
			// les << bonnes >> valeurs.  Cette ligne remplace donc les trois
			// suivantes mises en commentaire !
		// Point p1 = new Point();
		// p1.setX(1);
		// p1.setY(2);
		p1.afficher();
		System.out.println();
	}

}
