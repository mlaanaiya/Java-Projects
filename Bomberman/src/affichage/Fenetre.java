package affichage;
import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import game.GameLoop;
import menu.Menu;
import menu.InfosJoueur;
import controller.Input;
import game.Game;
import game.VariablesGlobales;
import map.GameMap;

public class Fenetre extends JFrame {

	private Canvas canvas;
	private Renderer renderer;
	private int width;
	private int height;
	private Input input;
	private Game game;
	private VariablesGlobales vg = new VariablesGlobales();
	private int wSX = vg.windowSizeX;
	private int wSY = vg.windowSizeY;

	/** Constructeur de la classe fenetre.
	 * @param width, largeur de la fenetre.
	 * @param height, longueur de la fenetre.
	 * @param input, saisie utilisateur.
	 */
	public Fenetre(int width, int height,Input input) {
		this.width = width;
		this.height = height;
		this.input = input;

		// Le Container de la fenêtre
		this.setBackground(Color.BLACK);
		Container contenu = this.getContentPane();
		contenu.setLayout(new BorderLayout());
		contenu.add(new Menu(this), BorderLayout.NORTH);
		contenu.add(new InfosJoueur(this), BorderLayout.CENTER);
		//
		setTitle("BomberMan");// Titre de la fenetre.
		setDefaultCloseOperation(EXIT_ON_CLOSE); // So that the windows closes AND the process ends too.
		setResizable(false);// Fenetre de taille fixe.
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height)); // Configuration de la taille de la fenetre.
		canvas.setFocusable(false);

		contenu.add(canvas, BorderLayout.SOUTH);
		//
		addKeyListener(input); // KeyListener pour les touches de controle.
		pack();
		canvas.createBufferStrategy(3);
		setLocationRelativeTo(null); // Centrage de la fenetre.
		setVisible(true); // Mettre la fenetre en visible.

	}


	public void render(Game game, GameMap gameMap) {
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		Graphics graphics = bufferStrategy.getDrawGraphics();

		renderer = new Renderer();

		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		renderer.render(game, graphics, gameMap);

		bufferStrategy.show();
	}

	public Game getGame() {
		return new Game(896,384);
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

}
