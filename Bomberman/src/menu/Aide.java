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
import affichage.msg;

public class Aide extends JMenu {
	public Fenetre frame;
	public Aide(Fenetre frame) {
		super("Aide");
		this.frame = frame;
		//Comment Jouer
		JMenuItem instructions = new JMenuItem("Tutoriel");
		instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		instructions.addActionListener(new MenuActionListener(frame));
		add(instructions);
		//Contact
		JMenuItem about = new JMenuItem("Contact");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		about.addActionListener(new MenuActionListener(frame));
		add(about);
	}

    	class MenuActionListener implements ActionListener {
        	public Fenetre frame;
        	public MenuActionListener(Fenetre frame) {
        	    	this.frame = frame;
        	}
        
          	@Override
        	public void actionPerformed(ActionEvent ev) {
              
              		if(ev.getActionCommand().equals("Tutoriel")) {
                  		new msg("Comment jouer?", "Mouvements : UP, DOWN, RIGHT, LEFT\nPut Bombs: ESPACE", JOptionPane.QUESTION_MESSAGE);
              		}
                  
              		else if(ev.getActionCommand().equals("Contact")) {
                  		new msg("Contact", "Version : 3" + "\n Mahmoud LAANAIYA" + "\n mahmoud.laanaiya@etu.inp-n7.fr", JOptionPane.INFORMATION_MESSAGE);
              		}
          	}
    	}
}
