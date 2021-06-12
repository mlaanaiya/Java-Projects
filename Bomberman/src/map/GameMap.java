package map;

import java.awt.Graphics;

import physique.Size;
import game.VariablesGlobales;
// Classe de la carte du jeu.
public class GameMap {

	private VariablesGlobales vg = new VariablesGlobales();
	private int lx = vg.lx;
	private int ly = vg.ly;
	/** Largeur en nombre de tuiles selon l'axe des abscisses. */
	private int lengthX;
	/** Longueur en nombre de tuiles selon l'axe des ordonnees. */
	private int lengthY;
	/** Matrice de tuiles sur la carte. */
	private Tile[][] tile;

	/** Constructeur de la carte du jeu.
	 * @param size, taille de la carte.
	 * Ici elle joue le role d'une seule carte, plus tard on implementera plusieurs
	 * pseudo-constructeurs pour construire les differents niveaux qu'on proposera.
	 */
	public GameMap(Size size) {
		/** Largeur en nombre de tuiles selon l'axe des abscisses. */
		lengthX = size.getWidth();
		/** Longueur en nombre de tuiles selon l'axe des ordonnees. */
		lengthY = size.getHeight();
		// Instanciation du tableau, sinon une exception NullPointerException, this.tile is null est levee.
		this.tile = new Tile[lengthX][lengthY];

		// Construire les blocs de bord, et les tuiles normales (gris clair) sur la carte.
		initMap();
		// Construire les blocs destructibles (en gris fonce) et blocs indestructibles (en noir), sur la carte.
		initMap1();
	}

	/** Getter du tableau de tuiles.
	 * @return tile, le tableau de tuiles formant la carte.
	 */
	public Tile[][] getTile() {
		return this.tile;
	}

	/** Getter d'une tuile depuis ses indices.
	 * @param i, premier indice de la tuile.
	 * @param j, second indice de la tuile.
	 * @return la tuile d'indices (i,j).
	 */
	public Tile getTile(int i, int j) {
		return tile[i][j];
	}

	public void setTile(int i, int j, String tileType) {
		tile[i][j].setType(tileType);
	}
	/** Getter du nombre de tuiles en largeur.
	 * @return lengthX, la largeur en tuiles de la carte.
	 */
	public int getlengthX() {
		int lx = this.lengthX;
		return lx;
	}

	/** Getter du nombre de tuiles en largeur.
	 * @return lengthX, la largeur en tuiles de la carte.
	 */
	public int getlengthY() {
		int ly = this.lengthY;
		return ly;
	}

	/** Construire les blocs de bord, et les tuiles normales (gris clair) sur la carte. */
	public void initMap() {
		for(int i = 0; i < this.getlengthX(); i++) {
			for(int j = 0 ; j <  this.getlengthY(); j++) {
				if( i == 0 || j == 0 || i ==  this.getlengthX() -1 || j ==  this.getlengthY() -1) {
					Tile tile = new Tile("Border");
					this.tile[i][j] = tile;
				} else {
					Tile tile = new Tile("Tile");
					this.tile[i][j] = tile;
				}
			}
		}
	}

	/** Setter de tuiles en destructible.
	 * @param i, premier indice de la tuile.
	 * @param j, second indice de la tuile.
	 */
	public void setBreakable(int i, int j) {
		tile[i][j] = new Tile("Breakable");
	}

	/** Setter de tuiles en indestructible.
	 * @param i, premier indice de la tuile.
	 * @param j, second indice de la tuile.
	 */
	public void setUnbreakable(int i, int j) {
		tile[i][j] = new Tile("Unbreakable");
	}

	/** Dessiner l'etat du jeu sur le canvas.
	 * @param graphics ????
	 */
	public void render(Graphics graphics) {
		for(int i = 0; i <  this.getlengthX(); i++) {
			for(int j = 0 ; j <  this.getlengthY(); j++) {
				graphics.drawImage(
						tile[i][j].getSprite(),
						i*lx,
						j*ly,
						null);
			}
		}
	}

	/** Construire les blocs du niveau de test 1. */
	public void initMap1() {

		// Génération aléatoire des blocs
		int[] randomb = new int[80];
		int[] randomu = new int[80];
		int[] randenb = new int[80];
		int[] randenu = new int[80];
		boolean ind = false;

		for(int i = 0; i < 80; i++) {
			randomb[i] = (int)(Math.random()*60) + 1;
		}

		for(int i = 0; i < 80; i++) {
			randomu[i] = (int)(Math.random()*60) + 1;
		}

		for(int i = 0; i < 80; i++) {
			if ((randomb[i] % 28 != 5 && randomu[i] % 18 != 16) && (randomb[i] % 28 != 16 && randomu[i] % 18 != 10) && (randomb[i] % 28 != 14 && randomu[i] % 18 != 14) && (randomb[i] % 28 != 11 && randomu[i] % 18 != 1) && ((28 - randomb[i]%28) != 5 && (18 - randomu[i]%18) != 16) && ((28 - randomb[i]%28) != 16 && (18 - randomu[i]%18) != 10) && ((28 - randomb[i]%28) != 14 && (18 - randomu[i]%18) != 14) && ((28 - randomb[i]%28) != 11 && (18 - randomu[i]%18) != 1)) {
				randenb[i] = randomb[i];
				randenu[i] = randomu[i];
			}
		}

		for(int i = 0; i < 80; i++) {
			if (randenb[i] % 28 != 0 && randenu[i] % 18 != 0 && (28 - randenb[i]%28) != 0 && (18 - randenu[i]%18) != 0) {
				if ((randenb[i] % 28 != 1 && randenu[i] % 18 != 1) && ((28 - randenb[i]%28) != 1 && (18 - randenu[i]%18) != 1)) {
				
							this.setBreakable(randenb[i]%28 , randenu[i]%18);
							this.setUnbreakable((28 - randenb[i]%28) , (18 - randenu[i]%18));

				}
			}
		}
	}
}
