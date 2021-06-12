package menu;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import affichage.Fenetre;
import game.Game;
import game.GameLoop;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.*;
import java.awt.event.*;
import java.awt.ActiveEvent.*;


public class Partie extends JMenu {

	public Fenetre frame;
	public Partie(Fenetre frame) {
		super("Partie");
		this.frame = frame;

		/*
		 * Nouveau Jeu
		 */
		JMenuItem newgame = new JMenuItem("Nouveau Jeu");
		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newgame.addActionListener(new MenuActionListener(frame));
		add(newgame);

		/*
		 * Quitter
		 */
		JMenuItem quit = new JMenuItem("Quitter");
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		quit.addActionListener(new MenuActionListener(frame));
		add(quit);
	}
	
	class MenuActionListener implements ActionListener {
		public Fenetre frame;
		public MenuActionListener(Fenetre frame) {
			this.frame = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  // Cas où on tape Nouveau Jeu
			  if(e.getActionCommand().equals("Nouveau Jeu")) {
				frame.dispose();
				MenuDemarrage demarreur = new MenuDemarrage();
				demarreur.Demarrer();
			  }
			  // Cas où on tape Quitter
			  if(e.getActionCommand().equals("Quitter")) {
				  System.exit(0);
			  }
		  }
	}
}
