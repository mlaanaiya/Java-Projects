package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import classesNonCompletes.BufferedImageLoader;
import controller.Controller;
import map.GameMap;
import map.Tile;
import physique.Position;
import game.VariablesGlobales;

public class Ennemi extends MovingObject {

	private boolean dead = false;
	private Player player;

	private BufferedImageLoader loader = new BufferedImageLoader();

	private final BufferedImage Sprite_Ennemy = this.loader.loadImage("res/sprites/units/perso/ennemy.png").getSubimage(192,128,32,32);

	private VariablesGlobales vg = new VariablesGlobales();
	private int lx = vg.lx;
	private int ly = vg.ly;

	public Ennemi(Controller controller, GameMap map, Position positionInitiale, Player player) {
		super(controller, null, map);
		// TODO Auto-generated constructor stub
		this.position = positionInitiale; // PosX et PosY doivent etre mutliples de
		this.movement = vg.mouvement1; // Variable Globale to be defined soon
		this.player = player;
	}

	private void tuerEnnemi() {
		// TODO Auto-generated method stub
		Tile tile = (this.map.getTile(pixel2index(this.position.getX())[0], pixel2index(this.position.getY())[0]));
		if((tile.getType()).equals("Explosion")) {
			this.setDead(true);
		}
	}

	public boolean tuerJoueur() {
		return detecterCollision();
	}


	private boolean detecterCollision() {

		// TODO Auto-generated method stub
		Position positionPlayer = this.player.getPosition();
		int posi = pixel2index((positionPlayer.getX()))[0];
		int posj = pixel2index((positionPlayer.getY()))[0];
		if(((posi - pixel2index(this.position.getX())[0])==0) && (posj == (pixel2jndex(this.position.getY())[0]))) {
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		position.appliquerEnnemi1(this.map);
		tuerEnnemi();
	}

	@Override
	public Image getSprite() {
		if(!isDead()){
			// TODO Auto-generated method stub
			Graphics2D graphics = this.Sprite_Ennemy.createGraphics();
			graphics.drawImage(this.Sprite_Ennemy, lx, ly, null);
			graphics.dispose();
			return this.Sprite_Ennemy;
		} else {
			return null;
		}
	}

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
		return jndex ;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
