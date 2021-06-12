package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import physique.Position;
import affichage.Fenetre;
import controller.Input;
import controller.PlayerController;
import gameObjects.Bomb;
import gameObjects.Ennemi;
import gameObjects.Player;
import map.GameMap;
import physique.Size;

import audio.AudioPlayer;
import game.Settings.AudioSettings;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.CopyOnWriteArrayList;
import graphique.SpriteLibrary;

public class Game {
	/* Fenetre dans le jeu. */
	private Fenetre display;
	private Player player;
	private Input input = new Input();
	private GameMap gameMap;
	private boolean pause;
	public List<Ennemi> ennemis = new ArrayList<Ennemi>();
	private SpriteLibrary spriteLibrary =  new SpriteLibrary();
	private AudioPlayer audioPlayer = new AudioPlayer(new AudioSettings());
	private VariablesGlobales vg = new VariablesGlobales();
	private int lx = vg.lx;
	private int ly = vg.ly;


	public Game(int width, int height) {
		display = new Fenetre(width,height,input);
		gameMap = new GameMap(new Size(width/lx,height/ly));
		player = new Player(new PlayerController(input), spriteLibrary, gameMap);
		initEnnemis();
		audioPlayer.playMusic("100.wav");
	}


	private void initEnnemis() {
		// TODO Auto-generated method stub
		initEnnemi(5,16);
		initEnnemi(16,10);
		initEnnemi(14,14);
		initEnnemi(11,1);
	}

	public void initEnnemi(int index, int jndex) {
		ennemis.add(new Ennemi(new PlayerController(new Input()),gameMap , new Position(index*lx,jndex*ly),player));
	}

	public Player getPlayer() {
		return this.player;
	}

	public void update() {
		setPause();
		if (!pause) {
			player.update();
			traiterInteractions();
		}
	}

	public void traiterInteractions(){
		this.ennemis.forEach(ennemi->ennemi.update());
		this.ennemis.forEach(ennemi->tuerJoueur(ennemi));
		destroyEnemy(this.ennemis);
	}

	public void setPause() {
		if(this.checkPause()) {
			this.setPause(true);
		}
		if(this.checkResume()) {
			this.setPause(false);
		}
	}

	public void destroyEnemy(List<Ennemi> ennemis) {
		for(int i=0; i<ennemis.size(); i++) {
			if(ennemis.get(i).isDead()) {
				ennemis.remove(i);
			}
		}
	}

	private void tuerJoueur(Ennemi ennemi) {
		// TODO Auto-generated method stub
		if(ennemi.tuerJoueur()) {
				this.player.setOver(true);
		}
	}

	public void render() {
		display.render(this,gameMap);
	}

	public void quitGame() {
		System.exit(0);
	}

	public boolean getPause() {
		boolean vpause = this.pause;
		return vpause;
	}

	public void setPause(boolean pause) {
        this.pause = pause;
	}

	public boolean checkPause() {
		return input.isPressed(KeyEvent.VK_P);
	}

	public boolean checkResume() {
		return input.isPressed(KeyEvent.VK_R);
	}

	public Fenetre getFenetre() {
		return this.display;
	}


	public List<Ennemi> getEnemies() {
		// TODO Auto-generated method stub
		return ennemis;
	}
}
