package physique;

// Classe du vecteur deplacement elementaire d'un objet.
public class VecteurDE {

	/** deplacement elementaire selon l'axe des abscisses. */
	private double dx;
	/** deplacement elementaire selon l'axe des ordonnees. */
	private double dy;
	
	/** Constructeur de classe vecteur deplacement elementaire.
	 * @param dx, deplacement elementaire selon l'axe des abscisses.
	 * @param dy, deplacement elementaire selon l'axe des ordonnees.
	 */
	public VecteurDE (double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/** Getter de dx.
	 * @return dx.
	 */
	public double getX() {
		return dx;
	}
	
	/** Getter de dy.
	 * @return dy.
	 */
	public double getY() {
		return dy;
	}
	
	/** Setter de dx.
	 * @param dx.
	 */
	public void setX(double dx) {
		this.dx = dx;
	}
	
	/** Setter de dy.
	 * @param dy.
	 */
	public void setY(double dy) {
		this.dy = dy;
	}

	/** Multiplication d'une constante (vitesse) par le vecteur DE.
	 * @param vitesse, un scalaire.
	 */
	public void multiplier(double vitesse) {
		dx = dx*vitesse;
		dy = dy*vitesse;
	}
	/** Avoirla longueur du vecteur.*/
	public double length() {
		return Math.sqrt(dx*dx + dy*dy);
	}
}
