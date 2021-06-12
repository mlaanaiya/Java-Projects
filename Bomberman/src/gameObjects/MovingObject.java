package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import graphique.*;
import controller.Controller;
import map.GameMap;
import map.Tile;
import physique.Mouvement;

public abstract class MovingObject extends GameObject {

	/** Controleur des touches utilisateur. */
	private Controller controller;

	/** Mouvement genere par la saisie utilisateur. */
	protected Mouvement movement;
 	private boolean bombDropped = false;
	private List<Bomb> bomb = new ArrayList<Bomb>();
	/** Manipuler les Sprites des objets du jeu*/
	private AnimationManager animationManager;
	/** direction du joueur*/
	private direction direction;
	protected GameMap map;
	int max = 1;
	private boolean over;
	//private Bomb bomb;
	//private direction direction;

	/** Coonstructeur du MovingObject.
	 * @param map
	 * @param controller, controleur de mouvement.
	 */
	public MovingObject(Controller controller, SpriteLibrary spriteLibrary, GameMap map) {
		super();
		this.controller = controller;
		this.movement = new Mouvement(4); //parametre : vitesse.
		this.direction = direction.S;
		this.animationManager = new AnimationManager(spriteLibrary.GetUnit("perso"));
		this.map = map;
		//this.direction = direction.sud;
	}

	/** Mettre a jour l'etat du MovingObject. */
	@Override
	public void update() {
		bombDropped = controller.dropBomb();
		if(bombDropped) {
			bomb.add(new Bomb(this.position, map));
			int index = bomb.size()-1;
			(bomb.get(index)).setBomb();
			(bomb.get(index)).setDropTime(System.currentTimeMillis());
			bombDropped = false;
			(bomb.get(index)).setStillDropped(true);
		}
		bomb.forEach(bombElement->gererBombes(bombElement));

		tuerJoueur();

		movement.update(controller);
		position.appliquer(movement,map);

		manageDirection();
		//deciderAnimation();
		animationManager.update(direction);
		movement.update(controller);
		position.appliquer(movement,map);

	}

	public void setOver(boolean dead) {
		this.over = dead;
	}

	public boolean getOver() {
		return this.over;
	}

	private void tuerJoueur() {
		// TODO Auto-generated method stub
		Tile tile = (this.map.getTile(pixel2index(this.position.getX())[0], pixel2index(this.position.getY())[0]));
		if((tile.getType()).equals("Explosion")) {
			//KILL PLAYER
			setOver(true);
		}

	}

	/**Decider quel animation a jouer selon le mouvement pour un joueur.*/
	/*private void deciderAnimation() {
		if(movement.isMoving()) {
			animationManager.playAnimation("walk");
		} else {
			animationManager.playAnimation("stand");
		}
	}*/

	/**Gestion de la direction de l'objet.*/
	private  void manageDirection() {
		if(movement.isMoving()) {
			this.direction = graphique.direction.fromMotion(movement);

		}
	}

	/** Dessiner l'image du MovingObject sur le canvas. */
	@Override
	public Image getSprite() {
		return animationManager.getSprite();
	}

	public boolean getBombIsDropped() {
		return bombDropped;

	}
	public int[] pixel2index(double x) {
		double realIndex = x/32;
		int[] index = {(int) Math.floor(realIndex),(int) Math.ceil(realIndex)};
		return index;
	}

	/** Extraire les indices "j" des tuiles entourant l'objet.
	 * @param y, position y (en pixels) de l'objet sur le canvas.
	 * @return jndex, un tableau contenant les indices "j" (correspondant aux ordonnees) des tuiles qui entourent l'objet.
	 */
	public int[] pixel2jndex(double y) {
		double realJndex = y/32;
		int[] jndex = {(int) Math.floor(realJndex),(int) Math.ceil(realJndex)};
		return jndex ;
	}

	public void gererBombes(Bomb bomb) {
		if(bomb.getStillDropped()) {
			double delta = (System.currentTimeMillis()-bomb.getDropTime());
			if(delta>bomb.getTimer()) {
				bomb.explode();
				bomb.setIsExploding(true);
				bomb.setStillDropped(false);
				bomb.setExplosionTime(System.currentTimeMillis());
			}
		}

		if(bomb.getIsExploding()) {
			double delta = (System.currentTimeMillis()-bomb.getExplosionTime());
			if(delta>bomb.getExplosionTimer()) {
				bomb.unsetBomb();
				bomb.setIsExploding(false);
			}
		}
	}

}
