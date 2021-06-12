package menu;
import javax.swing.*;
import java.awt.*;
import affichage.Fenetre;
import gameObjects.Player;

public class InfosJoueur extends JPanel{
	private JLabel timeLabel;
	private int timer = 200;
	public InfosJoueur(Fenetre jeu) {
		this.setBackground(Color.black);
		UIManager.put("ProgressBar.foreground", Color.ORANGE);
		UIManager.put("ProgressBar.selectionBackground", Color.ORANGE);
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
		timeLabel = new JLabel("Temps : " + timer);
		timeLabel.setForeground(Color.white);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		add(timeLabel);
		timer = timer - 1;
		/*while(timer >= 0) {
			remove(timeLabel);
			timeLabel = new JLabel("Temps : " + timer);
			timeLabel.setForeground(Color.white);
			timeLabel.setHorizontalAlignment(JLabel.CENTER);
			add(timeLabel);
			try {
				Thread.sleep(1);
				timer--;
			} catch(InterruptedException e) {
				System.out.printf("Hello");
			}
				
		}*/ //À faire dans l'Engine
		JProgressBar barredevie = new JProgressBar(0,100);
		barredevie.setPreferredSize(new Dimension(800,50));
		barredevie.setValue(100);
		barredevie.setBackground(Color.BLACK);
		barredevie.setStringPainted(true);		
		add(barredevie);
	}
}
