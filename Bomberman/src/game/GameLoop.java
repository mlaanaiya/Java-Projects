package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GameLoop implements Runnable {
	/** Booleen qui renvoie si le joue est toujours en cours. */
	private boolean running;
	/** Frequence de rafraichissement. */
	private final double refresh = 1.0/60.0;
	/** Le jeu sur lequel on agit. */
	private Game game;
	/**Temps d'exécution du jeu. */
	private double time;

	private VariablesGlobales vg = new VariablesGlobales();
	private int wSX = vg.windowSizeX;
	private int wSY = vg.windowSizeY;


	/** Constructeur de la boucle.
	 * @param game, le jeu en cours.
	 */
	public GameLoop(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		running = true;
		double accumulator = 0;
		long currentTime, lastUpdate = System.currentTimeMillis();

		while(running) {
			currentTime = System.currentTimeMillis();
			double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000.0;
			accumulator += lastRenderTimeInSeconds;
			lastUpdate = currentTime;

			if(accumulator >= refresh) {
				while( accumulator >= refresh) {
					if(this.game.getPause()) {
						renderScreen();
					}
					else if(this.game.getPlayer().getOver()) {
						renderOverScreen();
					} else if (this.game.getEnemies().isEmpty()) {
						renderWinScreen();
					}
					else{
						render();
					}
					update();
					accumulator -= refresh;
				}
			}

		}
	}

	/* Ecran affiche si le jeu en pause. */
	private void renderScreen() {
		BufferStrategy bs = game.getFenetre().getCanvas().getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Font font = new Font("Arial", Font.PLAIN, 20 * 3);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("PAUSED", wSX, wSY, g);
		g.dispose();
		bs.show();
	}

	/* Ecran afficher si le joueur meurt. */
	private void renderOverScreen() {
		BufferStrategy bs = game.getFenetre().getCanvas().getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Font font = new Font("Arial", Font.PLAIN, 20 * 3);
		g.setFont(font);
		g.setColor(Color.RED);
		drawCenteredString("GAME OVER", wSX, 300, g);
		drawCenteredString("NEW GAME ?", wSX, 450, g);
		drawCenteredString("PRESS CTRL-N", wSX, 600, g);
		g.dispose();
		bs.show();
	}

	/* Ecran afficher si le joueur meurt. */
	private void renderWinScreen() {
		BufferStrategy bs = game.getFenetre().getCanvas().getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Font font = new Font("Arial", Font.PLAIN, 20 * 3);
		g.setFont(font);
		g.setColor(Color.GREEN);
		drawCenteredString("Victory !", wSX, 300, g);
		drawCenteredString("NEW GAME ?", wSX, 450, g);
		drawCenteredString("PRESS CTRL-N", wSX, 600, g);
		g.dispose();
		bs.show();
	}

	public void drawCenteredString(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	 }

	/** Afficher l'image du jeu. */
	private void render() {
		game.render();
	}

	/** Mettre a jour l'etat du jeu. */
	private void update() {
		game.update();
	}

}
