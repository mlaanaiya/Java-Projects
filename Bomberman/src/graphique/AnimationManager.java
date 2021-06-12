package graphique;
import game.VariablesGlobales;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Une classe qui gére l'animation des sprites. */
	public class AnimationManager {
	private SpriteSet spriteSet;    // L'ensemble des sprites pour chaque objet animé.
	private BufferedImage currentAnimationSheet; //
	private int updatesPerFrame;//durée de  chaque sprite dans le SpriteSet.
	private int currentFrameTime; //index pour suivre l'évalution du temps pour chaque animation donné.    
	private int frameIndex;  // index pour manipuler les sprites dans la SpriteSet.
	private int directionIndice;
	private VariablesGlobales vg  = new VariablesGlobales();
	private int SPRITE_SIZE = vg.lx;

        //Constructeur de la classe.
	public AnimationManager(SpriteSet spriteSet) {
        	this.spriteSet = spriteSet;
        	this.updatesPerFrame = 20;
        	this.frameIndex = 0;
        	this.currentFrameTime = 0;
        	this.directionIndice = 0;
        	playAnimation("bomber32");
    	}


	//Determiner quel image a donner u joueur a un instant donné.
	public Image getSprite() {
        	return currentAnimationSheet.getSubimage(
                	frameIndex * 32,//on ajoute un pas de Game.SPRITE.SIZE pour avoir la sprite suivante
                	directionIndice * 32,//on ajoute un pas de Game.SPRITE.SIZE pour avoir la sprite suivante
                	32,//chaque sprite récupérée a les meme largeur.
                	32//chaque sprite récupérée a les meme hauteur.
        	);
    	}
	
	// Mise a jour de l'image aprés chaque updatesPerFrame.
    	public void update(direction direction) {
        	currentFrameTime++;
        	directionIndice = direction.getline();
        	if(currentFrameTime >= updatesPerFrame) {
            	currentFrameTime = 0;
            	frameIndex++;

            	if(frameIndex >= currentAnimationSheet.getWidth() / SPRITE_SIZE) {
                	frameIndex = 0;
            	}
        	}
    	}
	//Jouer une animation donnée.
    	public void playAnimation(String name) {
        	this.currentAnimationSheet = (BufferedImage) spriteSet.get(name);
    	}

}
