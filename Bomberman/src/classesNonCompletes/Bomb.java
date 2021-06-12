package classesNonCompletes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import controller.Input;
import gameObjects.GameObject;
import map.GameMap;
import map.Tile;
import physique.Position;

public class Bomb extends GameObject {
	
	private int timer;
	private int range;
	private GameMap map;
	private Input input;
	private boolean alive;
	int counter = 0;
		
	public Bomb(int Timer, int Range) {
		this.timer = Timer;
		this.range = Range;
		this.input = new Input();
	}
	
	public int getTimer() {
		return this.timer;
	}
	
	public int getRange () {
		return this.range;
	}
	
	public void damage () {
		int bomb_x = (int) this.getTilePosition().getX();
		int bomb_y = (int) this.getTilePosition().getY();
		boolean left = (bomb_y == 0);
		boolean right = (bomb_y == this.map.getlengthY());
		boolean up = (bomb_x == this.map.getlengthX());
		boolean down = (bomb_x == 0);
		int index = 1;
		
		// Damage the left area
		while (index < (range + 1) && ! left) {
			if (this.map.getTile()[bomb_x - index][bomb_y].getType().equals("Unbreakable") || this.map.getTile()[bomb_x - index][bomb_y].getType().equals("Border")) {
				left = true;
			} else {
				this.map.getTile()[bomb_x - index][bomb_y] = new Tile (null);
				index ++ ;
			}
		}
		// Damage the right area
		while (index < (range + 1) && ! right) {
			if (this.map.getTile()[bomb_x + index][bomb_y].getType().equals("Unbreakable") || this.map.getTile()[bomb_x - index][bomb_y].getType().equals("Border")) {
				right = true;
			} else {
				this.map.getTile()[bomb_x + index][bomb_y] = new Tile (null);
				index ++ ;
			}
		}
		// Damage the up area
		while (index < (range + 1) && ! up) {
			if (this.map.getTile()[bomb_x][bomb_y + index].getType().equals("Unbreakable") || this.map.getTile()[bomb_x - index][bomb_y].getType().equals("Border")) {
				up = true;
			} else {
				this.map.getTile()[bomb_x][bomb_y + index] = new Tile (null);
				index ++ ;
			}
		}
		
		while (index < (range + 1) && ! down) {
			if (this.map.getTile()[bomb_x][bomb_y - index].getType().equals("Unbreakable") || this.map.getTile()[bomb_x - index][bomb_y].getType().equals("Border")) {
				down = true;
			} else {
				this.map.getTile()[bomb_x][bomb_y - index] = new Tile (null);
				index ++ ;
			}
		}
		
	}
	
	// The Tile in which the bomb should explode
	private Position getTilePosition () {
		int x = this.getPosition().intX();
		int y = this.getPosition().intY();
		int nbx = x / 50;
		int nby = y / 50;
		return new Position(nbx, nby);
	}	
	
	@Override
	public void update() {
	//	if((this.input).dropBomb())
		this.damage();
	}
	
	@Override
	public Image getSprite() {
		return null;
	}
}






