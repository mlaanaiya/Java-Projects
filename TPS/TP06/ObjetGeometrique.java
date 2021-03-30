import java.awt.Color;

/** La classe ObjetGeometrique factorise les caractéristiques communes aux
  * différents éléments qui composent un schéma mathématique.
  *
  * <strong>Remarque :</strong> Nous définissons une classe et non une interface
  * car dans les caractéristiques communes il y a la couleur qui et un attribut
  * (et les deux méthodes associées).  Ce ne peut donc pas être une interface.
  *
  * @author  Xavier Crégut
  * @version 1.4
  */
public abstract class ObjetGeometrique {

	private Color couleur;	// couleur de l'objet géométrique

	/** Constructeur de ObjetGeometrique à partir de sa couleur.
	  * @param saCouleur la couleur de l'objet
	  */
	public ObjetGeometrique(Color saCouleur) {
		this.setCouleur(saCouleur);
	}

	/** Obtenir la couleur de l'objet.
	 * @param la couleur de l'objet
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/** Changer la couleur de l'objet.
	  * @param nouvelleCouleur nouvelle couleur
	  */
	public void setCouleur(Color nouvelleCouleur) {
		this.couleur = nouvelleCouleur;
	}

	/**  Afficher sur le terminal les caractéristiques de l'objet.  */
	public abstract void afficher();

	/** Translater l'objet géométrique.
	 *  @param dx déplacement suivant l'axe des X
	 *  @param dy déplacement suivant l'axe des Y
	 */
	public abstract void translater(double dx, double dy);

	/** Dessiner l'objet géométrique sur l'afficheur.
	  * @param afficheur l'afficheur à utiliser
	  */
	public abstract void dessiner(afficheur.Afficheur afficheur);

}
