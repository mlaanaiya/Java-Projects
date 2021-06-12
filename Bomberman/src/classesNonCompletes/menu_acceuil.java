package src;
import Game.Game;
import Game.GameLoop;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.*;
import java.awt.event.*;
import java.awt.ActiveEvent.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Display.Display;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import JImagePanel.*;

public class menu_acceuil {
	
	public static void menu_acceuil(JFrame fen, JImagePanel panel, int x, int y, Color color) {
		fen.setSize(x,y);
		fen.setContentPane(panel);
		panel.setLayout(null);
		fen.getContentPane().setBackground(color);
		fen.setVisible(true);
	}

	public static void setBars(JMenuBar MyMenuJouer, JMenuBar MyMenuParam,JMenuBar MyMenuQuit, JImagePanel panel) {
		panel.add(MyMenuJouer);
		panel.add(MyMenuParam);
		panel.add(MyMenuQuit);
	}
	
	public static void setButtons(JMenuBar MyMenuJouer, JMenuBar MyMenuParam,JMenuBar MyMenuQuit, 
			JMenuItem MenuJouer, JMenuItem MenuParam,JMenuItem MenuQuit) {
		MyMenuJouer.add(MenuJouer);
		MyMenuParam.add(MenuParam);
		MyMenuQuit.add(MenuQuit);
	}
	
	public static void setButtonsColor(JMenuItem MenuJouer, JMenuItem MenuParam,JMenuItem MenuQuit, Color color) {
		MenuQuit.setBackground(color);
		MenuParam.setBackground(color);
		MenuJouer.setBackground(color);
	}
	
	public static void setButtonsFont(JMenuItem MenuJouer, JMenuItem MenuParam,JMenuItem MenuQuit, Font font) {
		MenuQuit.setFont(font);
		MenuParam.setFont(font);
		MenuJouer.setFont(font);
	}
	
	public static void setButtonsX_Y(JMenuBar MyMenuJouer, JMenuBar MyMenuParam,JMenuBar MyMenuQuit, int x, int y) {
		MyMenuJouer.setBounds(x, y, 400, 100);
		MyMenuParam.setBounds(x, y+150, 400, 100);
		MyMenuQuit.setBounds(x, y+300, 400, 100);
	}
	
	public static void setButtonsSize(JMenuBar MyMenuJouer, JMenuBar MyMenuParam,JMenuBar MyMenuQuit, int w, int h) {
		MyMenuJouer.setSize(w, h);
		MyMenuParam.setSize(w, h);
		MyMenuQuit.setSize(w, h);
	}
	
	public static void MenuJouer(JFrame fen, JMenuItem MenuJouer) {
		MenuJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new GameLoop(new Game(800,600))).start();
				fen.dispose();
			}
		});
	}
	
	public static void MenuQuit(JFrame fen, JMenuItem MenuQuit) {
		MenuQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fen.dispose();
			}
		});
	}
	
}

