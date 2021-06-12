package menu;
import game.Game;
import game.GameLoop;
import game.Settings.AudioSettings;
import game.VariablesGlobales;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import affichage.JImagePanel;
import affichage.msg;
import javax.swing.JOptionPane;
import audio.*;

public class MenuDemarrage {
	public static int selectedButton;
	private JFrame fen = new JFrame("Bomberman");
	private JImagePanel panel = new JImagePanel("bomerman.png");
	// Setteur des barres de Menu
	private Font font = new Font("Serial", Font.BOLD, 20);
	private VariablesGlobales vg = new VariablesGlobales();
	private int wSX = vg.windowSizeX;
	private int wSY = vg.windowSizeY;
	private int[] quitPos = vg.quitButtonPosition;
	private int[] playPos = vg.playButtonPosition;
	private int[] paramPos = vg.parameterButtonPosition;
	private JMenuItem menuJouerItem = new JMenuItem("Jouer");
	private JMenuItem menuParamItem = new JMenuItem("Informations");
	private JMenuItem menuQuitItem = new JMenuItem("Quitter");


	public void Demarrer() {
		// Settings de la fenêtre
		setUpWindow(fen,panel);
		// Barre du Menu Jouer
		setUpMenu(menuJouerItem,playPos,panel);
		// Barre du Menu Options
		setUpMenuWithText(menuParamItem,paramPos,panel);
		// Barre du Menu Quitter
		setUpMenuExit(menuQuitItem,quitPos,panel);

		selectedButton = 1;

		//Detection du clavier
		fen.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				int KeyCode = e.getKeyCode();
				switch(KeyCode) {
					case KeyEvent.VK_UP:
						if (selectedButton > 1){
							selectedButton --;
							switch(selectedButton) {
								case 2:
									colorBackground(menuParamItem,menuJouerItem,menuQuitItem);
									break;
								case 1:
									colorBackground(menuJouerItem,menuParamItem,menuQuitItem);
									break;
							}
						}
						break;
					case KeyEvent.VK_DOWN:
						if (selectedButton < 3) {
							selectedButton ++;
							switch(selectedButton) {
								case 2:
									colorBackground(menuParamItem,menuJouerItem,menuQuitItem);
									break;
								case 3:
									colorBackground(menuQuitItem,menuParamItem,menuJouerItem);
									break;
							}
						}
						break;
					case KeyEvent.VK_ENTER:
						if (selectedButton==1) {
							new Thread(new GameLoop(new Game(wSX,wSY))).start();
							fen.dispose();
						} else if (selectedButton == 2) {
							new msg("Informations", "\nThis project was realized by :" + "\nMahmoud LAANAIYA\nAyoub LOUDYI\nMohamed Hamza LOTFI\nMohammed-Amine JAAFARI\nChouaib LAAOUINA\nMohamed IKICH\nAkram JANAH", JOptionPane.INFORMATION_MESSAGE);
						} else if (selectedButton == 3) {
							fen.dispose();
						}
						break;
	 			}
			}
		});

		//Background music
			AudioSettings audioSetting = new AudioSettings();
			AudioPlayer audioPlayer = new AudioPlayer(audioSetting);
			audioPlayer.playMusic("BGM.wav");
    }


		public void setUpMenuWithText(JMenuItem menuItem, int[] pos, JImagePanel panel) {
			JMenuBar menu =  new JMenuBar();
			panel.add(menu);
			menu.setBounds(pos[0], pos[1], pos[2], pos[3]);
			menu.add(menuItem);
			setUpMenuItemWithText(menuItem);
		}

		public void setUpMenuItemExit(JMenuItem menuItem) {
			menuItem.setBackground(Color.yellow);
			menuItem.setFont(font);
			menuItem.setBackground(Color.orange);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fen.dispose();
					System.exit(0);
				}
			});
		}


		public void setUpMenuItem(JMenuItem menuItem) {
			menuItem.setBackground(Color.yellow);
			menuItem.setFont(font);
			menuItem.setBackground(Color.orange);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Thread(new GameLoop(new Game(896,576))).start();
					fen.dispose();
				}
			});
		}

		public void setUpMenuExit(JMenuItem menuItem, int[] pos, JImagePanel panel) {
			JMenuBar menu =  new JMenuBar();
			panel.add(menu);
			menu.setBounds(pos[0], pos[1], pos[2], pos[3]);
			menu.add(menuItem);
			setUpMenuItemExit(menuItem);
		}
		
		public void setUpMenu(JMenuItem menuItem, int[] pos, JImagePanel panel) {
			JMenuBar menu =  new JMenuBar();
			panel.add(menu);
			menu.setBounds(pos[0], pos[1], pos[2], pos[3]);
			menu.add(menuItem);
			setUpMenuItem(menuItem);
		}

		public void setUpMenuItemWithText(JMenuItem menuItem) {
			menuItem.setBackground(Color.yellow);
			menuItem.setFont(font);
			menuItem.setBackground(Color.orange);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new msg("Informations", "\nThis project was realized by :" + "\nMahmoud LAANAIYA\nAyoub LOUDYI\nMohamed Hamza LOTFI\nChouaib LAAOUINA\nMohamed IKICH\nAmine JAAFARI", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}

		public void setUpWindow(JFrame fen, JImagePanel panel) {
			fen.setSize(wSX, wSY);
			fen.setContentPane(panel);
			panel.setLayout(null);
			fen.getContentPane().setBackground(Color.black);
			fen.setVisible(true);
			fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fen.setLocationRelativeTo(null);
			fen.setVisible(true);
		}

		public void colorBackground(JMenuItem menu1, JMenuItem menu2, JMenuItem menu3){
			menu1.setBackground(Color.yellow);
			menu2.setBackground(Color.orange);
			menu3.setBackground(Color.orange);

		}
}
