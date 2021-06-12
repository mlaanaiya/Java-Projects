package menu;
import javax.swing.JMenuBar;
import affichage.Fenetre;

public class Menu extends JMenuBar {
	/*Menu à l'intérieur du jeu */
	public Menu(Fenetre jeu) {
		add(new Partie(jeu));
		add(new Options(jeu));
		add(new Aide(jeu));
	}
}
