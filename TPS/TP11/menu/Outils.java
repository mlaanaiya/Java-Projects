package menu;

/** Opérations générales.
  * @author	Xavier Crégut
  * @version	1.1
  */
class Outils {

    /** Afficher un texte plusieurs fois. */
    public static void print(String texte, int nb) {
	for (int i = 0; i < nb; i++) {
	    System.out.print(texte);
	}
    }

    /** Afficher un texte plusieurs fois suivi d'un retour à la ligne. */
    public static void println(String texte, int nb) {
	print(texte, nb);
	System.out.println();
    }


}
