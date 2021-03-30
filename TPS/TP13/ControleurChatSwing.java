import javax.swing.*;
import java.awt.*;

/**
 * Le contrÃ´leur Swing.
 */
public class ControleurChatSwing extends JPanel {

	public ControleurChatSwing(Chat chat, String lePseudo) {
		super(new FlowLayout());

		// CrÃ©er les composants
		JLabel pseudo = new JLabel(lePseudo);
		JTextField texte = new JTextField(20);
		JButton bOK = new JButton("OK");

		// Les ajouter Ã  la vue du contrÃ´leur
		add(pseudo);
		add(texte);
		add(bOK);

		// DÃ©finir les contrÃ´leurs
		bOK.addActionListener(ev ->
				chat.ajouter(new MessageTexte(pseudo.getText(), texte.getText())));
	}

}
