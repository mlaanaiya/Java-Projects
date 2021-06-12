package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Console;

import game.VariablesGlobales;
import map.GameMap;
import map.Tile;
import physique.Position;

public class Bomb extends GameObject {

	private VariablesGlobales vg = new VariablesGlobales();
	private int lx = vg.lx;
	private int ly = vg.ly;
	private double timer = vg.timer;
	private double explosionTimer = vg.explosionTimer;
	private double dropTime = 0;
	private double explosionTime = 0;
	private int range = vg.bombRange;
	private GameMap map;
	private int posi;
	private int posj;
	boolean left = true;
	boolean right = true;
	boolean up = true;
	boolean down = true;
	private boolean bombStillDropped;
	private boolean bombIsExploding;

	public Bomb(Position position, GameMap map) {
		this.position = position;
		this.map = map;
		this.posi = (int) Math.floor(this.position.getX()/lx);
		this.posj = (int) Math.floor(this.position.getY()/ly);
		this.dropTime = System.currentTimeMillis();
	}

	public void setBomb() {
		this.map.setTile(posi,posj,"Bomb");
	}

	public void unsetBomb() {
		this.map.setTile(posi,posj,"Tile");
		// EXPLOSION
		int i = 1;
			right = true;
			left = true;
			up = true;
			down = true;
			while (i <= range & (left|right|up|down)) {
				if(right) {
					Tile currentTileRight = this.map.getTile(posi+i,posj);
					if((currentTileRight.getType()).equals("Border")|(currentTileRight.getType()).equals("Unbreakable")) {
						right = false;
					} else if((currentTileRight.getType()).equals("Explosion")) {
					// Implement Timer
						currentTileRight.setType("Tile");
					}
				}
				if(left) {
					Tile currentTileLeft = this.map.getTile(posi-i, posj);
					if((currentTileLeft.getType()).equals("Border")|(currentTileLeft.getType()).equals("Unbreakable")) {
						left = false;
					} else if((currentTileLeft.getType()).equals("Explosion")) {
					// Implement Timer
						currentTileLeft.setType("Tile");
					}
				}
				if(up) {
					Tile currentTileUp = this.map.getTile(posi,posj-i);
					if((currentTileUp.getType()).equals("Border")|(currentTileUp.getType()).equals("Unbreakable")) {
						up = false;
					} else if((currentTileUp.getType()).equals("Explosion")) {
					// Implement Timer
						currentTileUp.setType("Tile");
					}
				}
				if(down) {
					Tile currentTileDown = this.map.getTile(posi,posj+i);
					if((currentTileDown.getType()).equals("Border")|(currentTileDown.getType()).equals("Unbreakable")) {
						down = false;
					} else if((currentTileDown.getType()).equals("Explosion")) {
					// Implement Timer
						currentTileDown.setType("Tile");
					}
				}
				i++;
			}
	}

	public void explode() {
		// EXPLOSION
		int i = 1;
			while (i <= range & (left|right|up|down)) {
				if(right) {
					Tile currentTileRight = this.map.getTile(posi+i,posj);
					if((currentTileRight.getType()).equals("Border")|(currentTileRight.getType()).equals("Unbreakable")) {
						right = false;
					} else {
						// Implement Timer
						currentTileRight.setType("Explosion");
					}
				}
				if(left) {
					Tile currentTileLeft = this.map.getTile(posi-i, posj);
					if((currentTileLeft.getType()).equals("Border")|(currentTileLeft.getType()).equals("Unbreakable")) {
						left = false;
					} else {
						// Implement Timer
						currentTileLeft.setType("Explosion");
					}
				}
				if(up) {
					Tile currentTileUp = this.map.getTile(posi,posj-i);
					if((currentTileUp.getType()).equals("Border")|(currentTileUp.getType()).equals("Unbreakable")) {
						up = false;
					} else {
						// Implement Timer
						currentTileUp.setType("Explosion");
					}
				}
				if(down) {
					Tile currentTileDown = this.map.getTile(posi,posj+i);
					if((currentTileDown.getType()).equals("Border")|(currentTileDown.getType()).equals("Unbreakable")) {
						down = false;
					} else {
					// Implement Timer
						currentTileDown.setType("Explosion");
					}
				}
				i++;
			}

	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		explode();
	}

	@Override
	public Image getSprite() {
		BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.ORANGE);
		graphics.fillRect(0,0,size.getWidth(),size.getHeight());
		return image;
	}

	public double getTimer() {
		// TODO Auto-generated method stub
		return this.timer;
	}

	public double getExplosionTimer() {
		// TODO Auto-generated method stub
		return this.explosionTimer;
	}

	public void setDropTime(double time) {
		// TODO Auto-generated method stub
		this.dropTime = time;

	}

	public double getDropTime() {
		// TODO Auto-generated method stub
		double time = this.dropTime;
		return time;
	}

	public double getExplosionTime() {
		// TODO Auto-generated method stub
		double time = explosionTime;
		return time;
	}

	public void setExplosionTime(double time) {
		// TODO Auto-generated method stub
		this.explosionTime = time;
	}

	public void setStillDropped(boolean bool) {
		this.bombStillDropped = bool;
	}
	public boolean getStillDropped() {
		boolean bool = this.bombStillDropped;
		return bool;
	}
	public void setIsExploding(boolean bool) {
		this.bombIsExploding = bool;
	}
	public boolean getIsExploding() {
		boolean bool = this.bombIsExploding;
		return bool;
	}
}
