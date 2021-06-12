package game;
import physique.Mouvement;

public class VariablesGlobales {
	public Mouvement mouvement1 = new Mouvement(2);
	public int lx = 32;
	public int ly = 32;
	public int windowSizeX = 28*lx;
	public int windowSizeY = 20*ly;
	public int[] playButtonPosition = {7*lx, 8*ly, 14*lx, 3*ly};
	public int[] parameterButtonPosition = {7*ly, 12*lx, 14*lx, 3*ly};
	public int[] quitButtonPosition =  {7*lx, 16*ly, 14*lx, 3*ly};
	public double timer = 1500;
	public double explosionTimer = 500;
	public int bombRange = 3;

}
