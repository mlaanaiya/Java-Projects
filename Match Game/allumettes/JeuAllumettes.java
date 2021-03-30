package allumettes;

/**
 * La classe JeuAllumettes permet de jouer sans
 * utiliser le proxy.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class JeuAllumettes implements Jeu {

	/** Le nombre d'allumettes.*/
	private int nbAllumettes;

	/**
	* Constructeur de la classe JeuAllumettes.
	*
	* @param nombreAllumettes : Le nombre d'allumettes
	*/
	public JeuAllumettes(int nombreAllumettes) {
	        assert (nombreAllumettes >= 0);
	        this.nbAllumettes = nombreAllumettes;
	}

    	@Override
    	public int getNombreAllumettes() {
    	    return this.nbAllumettes;
    	}

    	/**
    	 * Changer le nombre d'allumettes.
    	 *
    	 * @param nombreAllumettes : le nombre d'allumettes
    	 */
    	public void setNombreAllumettes(int nombreAllumettes) {
		assert (nombreAllumettes > 0);
    	    	this.nbAllumettes = nombreAllumettes;
    	}

    	@Override
    	public void retirer(int nombrePrises) throws CoupInvalideException {
		int toto = Math.min(Jeu.PRISE_MAX, this.nbAllumettes);
        	if (nombrePrises > toto) {
                	throw new CoupInvalideException(nombrePrises, "> " + toto);
		} else if (nombrePrises <= 0) {
            		throw new CoupInvalideException(nombrePrises, "< 1");
		} else {
            		this.nbAllumettes -= nombrePrises;
		}

	}

}

