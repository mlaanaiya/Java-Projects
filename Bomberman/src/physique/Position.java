package physique;

import map.GameMap;
import game.VariablesGlobales;
// Classe de la position cartesienne d'un objet.
public class Position {

	private VariablesGlobales vg = new VariablesGlobales();
	private int lx = vg.lx;
	private int ly = vg.ly;

	/** Abscisse de l'objet sur le canvas. */
	private double x;
	/** Ordonnee de l'objet sur le canvas. */
	private double y;

	int direction = 1;

	/** Constructeur de la classe position.
	 * @param x, abscisse de l'objet.
	 * @param y, ordonnee de l'objet.
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Renvoie la valeur entiere proche de l'abscisse x.
	 * Utilise dans la classe Renderer par la methode drawImage qui necessite des pixels entiers.
	 * @return E(x). Ou E est l'operateur partie entiere.
	 */
	public int intX() {
		return (int) Math.round(x);
	}

	/** Getter de x.
	 * @return .
	 */
	public double getX() {
		return x;
	}

	/** Setter de x.
	 * @param x.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/** Renvoie la valeur entiere proche de l'ordonnee y.
	 * Utilise dans la classe Renderer par la methode drawImage qui necessite des pixels entiers.
	 * @return E(y). Ou E est l'operateur partie entiere.
	 */
	public int intY() {
		return (int) Math.round(y);
	}

	/** Getter de y.
	 * @return y.
	 */
	public double getY() {
		return y;
	}

	/** Setter de y.
	 * @param y.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/** Applique le mouvement retenu des entrees utilisateur a l'objet.
	 * @param movement, contient la vitesse, et le vecteur directeur du mouvement.
	 */
	/*public void appliquer(Mouvement movement) {
		VecteurDE vector = movement.getVecteur();

		double dx = vector.getX();
		double dy = vector.getY();
		// Verification de collision entre objets mouvants et objets fixes,
		//et changement de l'amplitude du vecteur deplacement en cas de collision.
		double[] dp = traiterCollision(dx,dy,new GameMap(new Size(16,12))); // Ce 10 devrait etre une constante.

		dx = dp[0];
		dy = dp[1];

		// Mise a jour de la position du joueur.
		x = x + dx;
		y = y + dy;
	}*/

	public void appliquer(Mouvement movement, GameMap map) {
		VecteurDE vector = movement.getVecteur();

		double dx = vector.getX();
		double dy = vector.getY();
		// Verification de collision entre objets mouvants et objets fixes,
		//et changement de l'amplitude du vecteur deplacement en cas de collision.
		double[] dp = traiterCollision(dx,dy,map); // Ce 10 devrait etre une constante.

		dx = dp[0];
		dy = dp[1];

		// Mise a jour de la position du joueur.
		x = x + dx;
		y = y + dy;
	}


	/*public double[] traiterCollision(double dx, double dy,GameMap gameMap) {
		// Les coordonnees du point atteint par le mouvement (sommet en haut a gauche).
		// On parle de sommet car la hitbox de l'objet jour est normalement
		//un rectangle pour l'instant c'est un carre.
		double destinationX = x+dx;
		double destinationY = y+dy;

		// On extrait les indices des tuiles entourant l'objet.
		int[] indexes = pixel2index(destinationX);
		int[] jndexes = pixel2jndex(destinationY);

		// Affectation des indices aux 4 sommets de l'objet.
		int i = indexes[0];
		int i1 = indexes[1];
		int j = jndexes[0];
		int j1 = jndexes[1];

		/** Si l'un des sommets chevauche une tuile occupee (contenant une structure qu'il ne peut pas traverser)
		 * alors on remets le deplacement elementaire a 0. */
		/*if(gameMap.getTile(i,j).isOccupied()||gameMap.getTile(i1,j).isOccupied()||gameMap.getTile(i,j1).isOccupied()||gameMap.getTile(i1,j1).isOccupied()) {
			dx = 0;
			dy = 0;
		}

		// On recupere les nouveaux deplacements elementaires.
		double[] dp = {dx,dy};
		return dp;
	}*/

	/** Applique le mouvement retenu des entrees utilisateur a l'objet.
	 * @param movement, contient la vitesse, et le vecteur directeur du mouvement.
	 */
	public void appliquerEnnemi1(GameMap map) {
		double dx = 1;
		double dy = 0;
		// Verification de collision entre objets mouvants et objets fixes,
		//et changement de l'amplitude du vecteur deplacement en cas de collision.
		double[] dp = traiterCollision(direction*dx,direction*dy,map);

		dx = dp[0];
		dy = dp[1];

		if(dx == 0) {
			direction = -direction;
		}

		// Mise a jour de la position du joueur.
		x = x + dx;
		y = y + dy;

	}


	public double[] traiterCollision(double dx, double dy,GameMap gameMap) {
		// Les coordonnees du point atteint par le mouvement (sommet en haut a gauche).
		/** On parle de sommet car la hitbox de l'objet jour est normalement
		 * un rectangle pour l'instant c'est un carre. */
		double destinationX = x+dx;
		double destinationY = y+dy;

		// On extrait les indices des tuiles entourant l'objet.
		int[] indexes = pixel2index(destinationX);
		int[] jndexes = pixel2jndex(destinationY);

		// Affectation des indices aux 4 sommets de l'objet.
		int i = indexes[0];
		int i1 = indexes[1];
		int j = jndexes[0];
		int j1 = jndexes[1];

		/** Si l'un des sommets chevauche une tuile occupee (contenant une structure qu'il ne peut pas traverser)
		 * alors on remets le deplacement elementaire a 0. */
		if(gameMap.getTile(i,j).isOccupied()||gameMap.getTile(i1,j).isOccupied()||gameMap.getTile(i,j1).isOccupied()||gameMap.getTile(i1,j1).isOccupied()) {
			dx = 0;
			dy = 0;
		}

		// On recupere les nouveaux deplacements elementaires.
		double[] dp = {dx,dy};
		return dp;
	}

	/** Extraire les indices "i" des tuiles entourant l'objet.
	 * @param x, position x (en pixels) de l'objet sur le canvas.
	 * @return index, un tableau contenant les indices "i" (correspondant aux abscisses) des tuiles qui entourent l'objet.
	 */
	public int[] pixel2index(double x) {
		double realIndex = x/lx;
		int[] index = {(int) Math.floor(realIndex),(int) Math.ceil(realIndex)};
		return index;
	}

	/** Extraire les indices "j" des tuiles entourant l'objet.
	 * @param y, position y (en pixels) de l'objet sur le canvas.
	 * @return jndex, un tableau contenant les indices "j" (correspondant aux ordonnees) des tuiles qui entourent l'objet.
	 */
	public int[] pixel2jndex(double y) {
		double realJndex = y/ly;
		int[] jndex = {(int) Math.floor(realJndex),(int) Math.ceil(realJndex)};
		return jndex;
	}

}
