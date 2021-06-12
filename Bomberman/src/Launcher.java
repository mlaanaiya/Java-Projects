import game.Game;
import game.GameLoop;
import game.Settings.AudioSettings;
import menu.MenuDemarrage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import affichage.JImagePanel;
import affichage.msg;
import javax.swing.JOptionPane;
import audio.*;

public class Launcher {

	public static void main(String[] args) {

		MenuDemarrage demarreur = new MenuDemarrage();
		demarreur.Demarrer();
	}
}
