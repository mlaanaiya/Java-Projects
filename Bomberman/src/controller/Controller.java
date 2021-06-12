package controller;

public interface Controller {
	/** Renvoie si la touche fleche haut a ete / est toujours pressee. */
	boolean isUp();
	
	/** Renvoie si la touche fleche bas a ete / est toujours pressee. */
	boolean isDown();
	
	/** Renvoie si la touche fleche gauche a ete / est toujours pressee. */
	boolean isLeft();
	
	/** Renvoie si la touche fleche droite a ete / est toujours pressee. */
	boolean isRight();
	
	/** Renvoie si la touche espace a ete / est toujours pressee. */
	boolean dropBomb();
}
