package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;
import classesNonCompletes.BufferedImageLoader;
import classesNonCompletes.SpriteSheet;
import graphique.AnimationManager;
import physique.Size;
import game.VariablesGlobales;

// Classe de tuile.
public class Tile {
	private VariablesGlobales vg = new VariablesGlobales();
	private int lx = vg.lx;
	private int ly = vg.ly;
	/** type du bloc qui occupe la tuile. */
	private String type; // Peut etre instancie par une enumeration de type de blocs qu'on a.

	/** taille de la tuile. */
	private Size size;

	/** Constructeur de la classe tuile.
	 * @param type, le type de bloc occupant la tuile.
	 */
	public Tile (String type) {
		this.type = type;
		this.size = new Size(lx,ly);
		// les valeurs 32 doivent etre representees par une constante width_block et height_block.
	}

	/* Les loaders des listes d'images */
	private BufferedImageLoader loader = new BufferedImageLoader();

	/* Un tableau des listes de sprites telecharges depuis chaque fichier */
	private BufferedImage[] SpriteList = {this.loader.loadImage("res/sprites/units/perso/tile_list_1.png"),    		this.loader.loadImage("res/sprites/units/perso/tile_list_2.png"), this.loader.loadImage("res/sprites/units/perso/tile_list_3.png"), this.loader.loadImage("res/sprites/units/perso/tile_list_4.png"), this.loader.loadImage("res/sprites/units/perso/bomb6.png"), this.loader.loadImage("res/sprites/units/perso/BombExploding.png")};

	/* Sprites des blocs indestructibles */
	private final BufferedImage Sprite_Unbreak = this.SpriteList[2].getSubimage(lx,8*ly,lx,ly);

	/* Sprites des blocs destructibles */
	private final BufferedImage Sprite_Break = this.SpriteList[3].getSubimage(lx,7*ly,lx,ly);

	/* Sprites des bords de la carte */
	private final BufferedImage Sprite_Border = this.SpriteList[2].getSubimage(lx,8*ly,lx,ly);

	/* Sprites de la  bombe */
	private final BufferedImage Sprite_Bomb = this.SpriteList[4].getSubimage(0,0,lx,ly);

	/* Sprite d'une tuile normale */
	private final BufferedImage Sprite_Tile = this.SpriteList[0].getSubimage(5*lx,4*ly,lx,ly);

	/* Sprite de l'explosion */
	private final BufferedImage Sprite_Fire = this.SpriteList[5].getSubimage(7*lx,ly,lx,ly);


	/** Dessine sur le canvas la tuile.
	 * @return une image dessinee sur le canvas.
	 */
	public Image getSprite() {
		BufferedImage Block_image = new BufferedImage(size.getWidth(), size.getHeight(),BufferedImage.TYPE_INT_RGB);
		if(type.equals("Unbreakable")) { // Indestructible
			Block_image = this.Sprite_Unbreak;
			Graphics2D graphics = this.Sprite_Unbreak.createGraphics();
			drawSprite(Block_image,graphics);
		} else if(type.equals("Breakable")) { // Destructible
			Block_image = this.Sprite_Break;
			Graphics2D graphics = this.Sprite_Break.createGraphics();
			drawSprite(Block_image,graphics);
		} else if(type.equals("Border")) { //Bords
			Block_image = this.Sprite_Border;
			Graphics2D graphics = this.Sprite_Border.createGraphics();
			drawSprite(Block_image,graphics);
		} else if(type.equals("Bomb")) { // Bombe
			Block_image = this.Sprite_Bomb;
			Graphics2D graphics = this.Sprite_Bomb.createGraphics();
		} else if(type.equals("Explosion")) { // Explosion
			Block_image = this.Sprite_Fire;
			Graphics2D graphics = this.Sprite_Fire.createGraphics();
			drawSprite(Block_image,graphics);
		} else { // Tile
			Block_image = this.Sprite_Tile;
			Graphics2D graphics = this.Sprite_Tile.createGraphics();
			drawSprite(Block_image,graphics);
		}
		return Block_image;
	}


	/** Renvoie si un bloc est occupe ou pas;
	 * @return true si c'est le cas, et false sinon.
	 * Les blocs occupes sont les bords, et les bloc destructibles et indestructibles.
	 */
	public boolean isOccupied() {
		if(type.equals("Border") || type.equals("Breakable") || type.equals("Unbreakable")) {
			return true;
		} else {
			return false;
		}
	}

	/** Renvoie le type de la tuile.
	 * @return type, le type de la tuile.
	 */
	public String getType () {
		return this.type;
	}

	/** Configure le type de la tuile.
	 * @return type, le type de la tuile.
	 */
	public void setType(String type) {
		this.type = type;
	}

	public void drawSprite(BufferedImage Block_image, Graphics2D graphics) {
		graphics.drawImage(Block_image, lx, ly, null);
		graphics.dispose();
	}
}
