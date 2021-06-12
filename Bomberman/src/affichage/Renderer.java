package affichage;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import game.Game;
import gameObjects.Ennemi;
import gameObjects.Player;
import map.GameMap;

public class Renderer {
	// Dessiner sur le canvas, l'etat actuel du jeu.
	public void render(Game game, Graphics graphics, GameMap gameMap) {
		// Afficher la carte
		gameMap.render(graphics);

		// Afficher le joueur
		Player player = game.getPlayer();
		List<Ennemi> ennemis = game.getEnemies();
ennemis.forEach(ennemi->graphics.drawImage(ennemi.getSprite(),ennemi.getPosition().intX(),ennemi.getPosition().intY(),null));
		graphics.drawImage(player.getSprite(),player.getPosition().intX(),player.getPosition().intY(),null);
	}

}
