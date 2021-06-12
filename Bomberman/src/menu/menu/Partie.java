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
	//this.setFont(new Font("MV Boli", Font.BOLD,20));
	//this.setFont(new Font("MV Boli", Font.BOLD,20));
	//this.setFont(new Font("MV Boli", Font.BOLD,20));
	public Partie(Fenetre frame) {
		super("Partie");
		this.frame = frame;
		/* Nouveau Jeu */
		JMenuItem brtia = new JMenuItem("Nouveau Jeu");
		brtia.addActionListener(new MenuActionListener(frame));
		add(brtia);
		
		/* Quitter */
		JMenuItem quit = new JMenuItem("Quitter");
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
			  Game game = (this.frame).getGame();
			  // Cas où on tape Nouveau Jeu
			  if(e.getActionCommand().equals("Nouveau Jeu")) {
				frame.dispose();
				new Thread(new GameLoop(game)).start();
			  }
			  // Cas où on tape Quitter
			  if(e.getActionCommand().equals("Quitter")) {
				  game.quitGame();
			  }

		  }
	}

}
