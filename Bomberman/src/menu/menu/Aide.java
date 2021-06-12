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

	public Aide(Fenetre frame)  {
		super("Aide");
		
		/*
		 * How to play
		 */
		JMenuItem instructions = new JMenuItem("Tutoriel");
		instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		instructions.addActionListener(new MenuActionListener(frame));
		add(instructions);
		
		/*
		 * Credits
		 */
		JMenuItem about = new JMenuItem("Contact");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		about.addActionListener(new MenuActionListener(frame));
		add(about);
		
	}
	
	class MenuActionListener implements ActionListener {
		public Fenetre _frame;
		public MenuActionListener(Fenetre frame) {
			_frame = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("Tutoriel")) {
				  new msg("How to Play", "Movement: UP,DOWN, RIGHT, LEFT\nPut Bombs: SPACE, X", JOptionPane.QUESTION_MESSAGE);
			  }
				  
			  if(e.getActionCommand().equals("Contact")) {
				  new msg("Contact", "\n Author: Mahmoud lm9wd\n Website: www.xnxx.com", JOptionPane.INFORMATION_MESSAGE);
			  }
			  
		  }
	}
}
