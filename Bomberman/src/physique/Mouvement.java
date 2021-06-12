package physique;

import controller.Controller;

// Classe du mouvement de l'objet. Toutes les entrees de l'utilisateur concernant le mouvement sont traites ici.
public class Mouvement {

	/** Vecteur directeur du mouvement. */
	private VecteurDE vecteurDE;
	/** Vitesse de deplacement de l'objet. */
	private double vitesse;
	
	/** Constructeur de la classe Mouvement.
	 * @param vitesse, la vitesse de deplacement de l'objet.
	 */
	public Mouvement(double vitesse) {
		this.vitesse = vitesse;
		
		// Par defaut, au depart l'objet est inanime donc a un vecteur directeur nul.
		this.vecteurDE = new VecteurDE(0,0);
		
	}
	
	/** Mets a jour la position de l'objet (dans notre cas ce sera le joueur).
	 * @param controleur, contient des methodes renvoyant quels boutons on ete tapes par l'utilisateur.
	 */
	public void update(Controller controleur) {
		
		int dx = 0;
		int dy = 0;
		
		// Traitement des entrees de l'utilisateur.
		if(controleur.isUp()) {
			dy--;
		} else if(controleur.isDown()) {
			dy++;
		} else if(controleur.isLeft()) {
			dx--;
		} else if(controleur.isRight()) {
			dx++;
		}

		// Affectation du nouveau vecteur directeur.
		vecteurDE = new VecteurDE(dx,dy);
		// On l'amplifie a la vitesse de l'objet, car(VecteurDE est unitaire).
		vecteurDE.multiplier(vitesse);
		
	}

	/** Getter du vecteur directeur du mouvement.
	 * @return vecteurDE, le vecteur directeur du mouvement.
	 */
	public VecteurDE getVecteur() {
		return vecteurDE;
	}
	/** Savoir si le personnage est en mouvement ou non.*/
	public boolean isMoving() {
		return vecteurDE.length() > 0;
	}
}
