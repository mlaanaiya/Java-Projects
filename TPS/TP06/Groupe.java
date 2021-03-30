import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/** Un groupe d'objets géométriques.
  *
  * @author  Xavier Crégut
  * @version 1.6
  */
public class Groupe extends ObjetGeometrique {

	private List<ObjetGeometrique> elements;	// les objets géométriques du groupe

	/** Construire un groupe avec une certaine capacité.
	 * @param capacite capacité du groupe
	 */
	public Groupe(int capacite) {
		super(Color.green);
		this.elements = new ArrayList<>(capacite);
	}

	/** Ajouter un objet dans un groupe.
	* @param f objet à ajouter dans le groupe
	*/
	//@ requires f != this;	// ne pas s'ajouter dans lui-même
	//@ requires !(f instanceof Groupe) || ! ((Groupe) f).deepContient(this);
	//@ 			// pas de cycle
	//@ ensures this.contient(f);	// objet ajouté
	public void ajouter(ObjetGeometrique f) {
		this.elements.add(f);
	}

	public void afficher() {
		System.out.print("Groupe{{ ");
		for (ObjetGeometrique objet : this.elements) {
			objet.afficher();
			System.out.print(" ");
		}
		System.out.print("}}");
	}

	public void dessiner(afficheur.Afficheur e) {
		for (ObjetGeometrique objet : this.elements) {
			objet.dessiner(e);
		}
	}

	public void translater(double dx, double dy) {
		for (ObjetGeometrique objet : this.elements) {
			objet.translater(dx, dy);
		}
	}

	public int taille() {
		return this.elements.size();
	}

	/** Supprimer un objet d'un groupe.
	 * @param f objet à supprimer du groupe
	 */
	//@ requires this.contient(f);	// s appartient au groupe
	//@ ensures ! this.contient(f);	// f a été supprimée du groupe
	public void oter(ObjetGeometrique f) {
		this.elements.remove(f);
	}

	public boolean contient(ObjetGeometrique objet) {
		return this.elements.contains(objet);
	}

	public boolean deepContient(ObjetGeometrique cherche) {
		boolean result = false;
		for (ObjetGeometrique objet : this.elements) {
			if (objet == cherche		// objet dans le groupe
					|| (objet instanceof Groupe	// ou dans un sous-groupe
							&& ((Groupe) objet).deepContient(cherche)))
			{
				return true;
			}
		}
		return false;
	}

}
