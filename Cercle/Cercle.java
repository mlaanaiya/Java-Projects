import java.awt.Color;

/** Un cercle est une courbe plane fermée constituée
 * des points situés à égale distance d’un pointnommécentre.
 * La valeur de cette distance est appeléerayondu cercle. On
 * appelle diamètre ladistance entre deux points diamétralement
 * opposés. La valeur du diamètre est donc le double dela valeur
 * du rayon.
 * @author  LAANAIYA Mahmoud <mahmoud.laanaiya@etu.inp-n7.fr>
 */

public class Cercle implements Mesurable2D {
    /** Centre du cercle.*/
    private Point centre;
    /** Rayon du cercle.*/
    private double rayon;
    /** Couleur du cercle.*/
    private Color couleur;
    /**Introduire la constante PI.*/
    public static final double PI = Math.PI;

    /** Construire un cercle à partir de son centre et de son rayon.
     * @param centre centre
     * @param r rayon
     */
    public Cercle(Point centre, double r) {
	assert (centre != null && r > 0);
	Point init = new Point(centre.getX(), centre.getY());
	this.centre = init;
	this.rayon = r;
	this.couleur = Color.blue;
    }

    /** Construire un cercle à partir deux points.
     * @param p1 premier point
     * @param p2 deuxième point
     */
    public Cercle(Point p1, Point p2) {
	notMemePoint(p1, p2);
	this.centre = new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
	this.rayon = p1.distance(p2) / 2;
        this.couleur = Color.blue;
    }

    /** Construire un cercle à partir de deux points et une couleur.
     * @param p1 premier point
     * @param p2 deuxième point
     * @param couleur couleur du cercle
     */
    public Cercle(Point p1, Point p2, Color couleur) {
	this(p1, p2);
        this.couleur = couleur;
    }

    /** Construire un cercle à partir de son centre et d´un point.
     * @param centre centre
     * @param p point appartenant à la circonférence
     * @return un cercle
     */
    public static Cercle creerCercle(Point centre, Point p) {
	notMemePoint(centre, p);
	return new Cercle(centre, p.distance(centre));
    }

    /** Imposer deux points différents.
     * @param p1 premier point
     * @param p2 deuxième point
     */
    static void notMemePoint(Point p1, Point p2) {
	assert (p1 != null && p2 != null && p1 != p2);
	assert (p1.getX() != p2.getX() || p1.getY() != p2.getY());
    }

    /** Translater le cercle.
     * @param dx déplacement suivant l'axe des X
     * @param dy déplacement suivant l'axe des Y
     */
    public void translater(double dx, double dy) {
        this.centre.translater(dx, dy);
    }

    /** Obtenir le centre du cercle.
     * @return centre du cercle
     */
    public Point getCentre() {
        Point p = new Point(this.centre.getX(), this.centre.getY());
        return p;
    }

    /** Obtenir le rayon du cercle.
     * @return rayon du cercle
     */
    public double getRayon() {
        return this.rayon;
    }

    /** Obtenir le diamètre du cercle.
     * @return diamètre du cercle
     */
    public double getDiametre() {
        return this.rayon * 2;
    }

    /** Verifier si un point est à l´intérieur du cercle.
     * @param p le point qu´on a à vérifier.
     * @return si le point appartient au cercle ou pas.
     */
    public boolean contient(Point p) {
	assert (p != null);
	Point c = new Point(this.centre.getX(), this.centre.getY());
	return (this.rayon >= p.distance(this.centre));
    }

    /** Obtenir la couleur du cercle.
     * @return la couleur du cercle
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /** Changer la couleur du cercle.
     * @param couleur nouvelle couleur
     */
    public void setCouleur(Color couleur) {
        assert (couleur != null);
        this.couleur = couleur;
    }

    /** Obtenir le perimetre du cercle.
     * @return perimetre du cercle
     */
    public double perimetre() {
        return this.rayon * 2 * PI;
    }

    /** Obtenir l´aire du cercle.
     * @return aire du cercle
     */
    public double aire() {
        return Math.pow(this.rayon, 2) * PI;
    }

    /** Écrire un cercle.
     * @return cercle en string
     */
    public String toString() {
        return ("C" + this.rayon + "@" + this.centre.toString());
    }

    /** Afficher le cercle. */
    public void afficher() {
	System.out.print(this);
    }

    /** Changer le diamètre du cercle.
     * @param diametre nouveau diamètre
     */
    public void setDiametre(double diametre) {
        assert (diametre > 0);
        this.rayon = diametre / 2;
    }

    /** Changer le rayon du cercle.
     * @param rayon nouveau rayon
     */
    public void setRayon(double rayon) {
        assert (rayon > 0);
        this.rayon = rayon;
    }
}
