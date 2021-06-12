package affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class msg implements WindowListener {

 	public msg(String title, String message, int option) {
 
 		final JFrame dialog = new JFrame(title);  
       		final JButton button = new JButton("Ok");  
       		button.addActionListener(new ActionListener() {  
           		@Override
			public void actionPerformed(ActionEvent e) {  
               			dialog.dispose();  
           		}  
       		});
       		JButton[] buttons = { button };  
       		JOptionPane optionPane = new JOptionPane(message, option, 0, null, buttons, button);  
       		dialog.getContentPane().add(optionPane);  
       		dialog.setSize(500,300);  
       		dialog.setLocationRelativeTo(null);  
       		dialog.setVisible(true);  
       		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       		dialog.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}
