package src;
import menu_acceuil.*;
import Game.Game;
import Game.GameLoop;
import affichage.JImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.*;
import java.awt.event.*;
import java.awt.ActiveEvent.*;

import Display.Display;

public class Lanceur extends menu_acceuil {

	public static void main(String[] args) {
		JImagePanel panel = new JImagePanel("bomerman.png");
		JMenuBar MyMenuJouer = new JMenuBar();
		JMenuBar MyMenuParam = new JMenuBar();
		JMenuBar MyMenuQuit = new JMenuBar();
		JMenuItem MenuJouer = new JMenuItem("Jouer");
		JMenuItem MenuParam = new JMenuItem("Options");
		JMenuItem MenuQuit= new JMenuItem("Quitter");
		Font font = new Font("Serial", Font.BOLD, 20);
		JFrame fen = new JFrame("Bomberman");

		menu_acceuil(fen, panel, 1920,1080,Color.black);
		setButtons(MyMenuJouer,  MyMenuParam, MyMenuQuit, 
				 MenuJouer, MenuParam, MenuQuit);
		setBars(MyMenuJouer, MyMenuParam, MyMenuQuit, panel);
		setButtonsColor(MenuJouer, MenuParam, MenuQuit, Color.orange);
		setButtonsFont(MenuJouer, MenuParam, MenuQuit, font);
		setButtonsX_Y(MyMenuJouer, MyMenuParam, MyMenuQuit, 710, 500);
		setButtonsSize(MyMenuJouer, MyMenuParam, MyMenuQuit, 400, 100);
		MenuJouer(fen, MenuJouer);
		MenuQuit(fen, MenuQuit);
	}
}
