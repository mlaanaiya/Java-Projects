package allumettes;

/**
 * La classe JeuMandataire présente le proxy.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */

public class JeuMandataire implements Jeu {

	/** Le jeu.*/
	private Jeu jeu;


	/**
     	* Constructeur de la classe JeuMandataire.
     	*
     	* @param jeu
     	*/
    	public JeuMandataire(Jeu jeu) {
        	assert (jeu != null);
        	this.jeu = jeu;
    	}


    	@Override
    	public int getNombreAllumettes() {
        	return this.jeu.getNombreAllumettes();
    	}


    	@Override
    	public void retirer(int nbPrises) throws OperationInterditeException {
        	throw new OperationInterditeException("[Je triche...]");
    	}

}

