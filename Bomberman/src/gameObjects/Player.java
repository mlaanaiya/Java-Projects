package gameObjects;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import controller.Controller;
import graphique.SpriteLibrary;
import physique.Mouvement;
import map.GameMap;

import java.awt.*;

public class Player  extends MovingObject {

	public Player(Controller controller, SpriteLibrary spriteLibrary, GameMap map) {
		super(controller, spriteLibrary, map);
	}

	@Override
	public void update() {		
		
		super.update();
	}


}
