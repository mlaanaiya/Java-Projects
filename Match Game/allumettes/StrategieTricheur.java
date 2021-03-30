package allumettes;

/**
 * La classe StrategieTricheur définit un joueur qui triche.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class StrategieTricheur implements Strategie {


	@Override
	public String getNom() {
		return "tricheur";
	}

	@Override
	public int prise(Jeu jeu, Joueur j) throws CoupInvalideException {
		assert (jeu != null && j != null);
	        int i;
	        int n = jeu.getNombreAllumettes() - 2;
	        try {
	            for (i = 0; i < n; i++) {
	                jeu.retirer(1);
	            }
	        } catch (CoupInvalideException e) {
	            System.out.println(e.getMessage());
	        }
	        return 1;
	}

}
