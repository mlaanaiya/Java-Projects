package menu;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import game.Game;
import game.GameLoop;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.*;
import java.awt.event.*;
import java.awt.ActiveEvent.*;
import affichage.Fenetre;

public class Options extends JMenu {

	Fenetre _frame;
	
	public Options(Fenetre frame) {
		super("Options");
		
		_frame = frame;
		
		JMenuItem pause = new JMenuItem("Pause");
		pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		add(pause);
		
		JMenuItem resume = new JMenuItem("Resume");
		resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		add(resume);
		
	}
	class MenuActionListener implements ActionListener {
		public Fenetre _frame;
		public MenuActionListener(Fenetre frame) {
			_frame = frame;
		}

		  @Override
		public void actionPerformed(ActionEvent e) {
			  Game game = _frame.getGame();
			  if(e.getActionCommand().equals("Pause")) {
				  _frame.getGame().setPause(!(_frame.getGame().pause == true));
			  }
				  
			  if(e.getActionCommand().equals("Resume")) {
				  _frame.getGame().setPause(!(_frame.getGame().pause == false));
			  }
		  }
	}
}






