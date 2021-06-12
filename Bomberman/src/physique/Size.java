package physique;

// Classe definissant la taille d'un objet.
public class Size {
	/** largeur d'un objet. */
	private int width;
	/** longueur d'un objet. */
	private int height;
	
	/** Constructeur de la taille d'un objet.
	 * @param width, largeur de l'objet.
	 * @param height, hauteur de l'objet.
	 */
	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/** Getter de la largeur.
	 * @return width, la largeur de l'objet.
	 */
	public int getWidth() {
		int w = width;
		return w;
	}
	
	/** Setter de la largeur.
	 * @param width, largeur de l'objet.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/** Getter de la longueur.
	 * @return height, la longueur de l'objet.
	 */
	public int getHeight() {
		int h = height;
		return h;
	}
	
	/** Setter de la longueur.
	 * @param height, longueur de l'objet.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
}
