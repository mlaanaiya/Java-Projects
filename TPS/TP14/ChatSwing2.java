import javax.swing.*;
import java.awt.*;

/**
 * Une interface utilisateur en Swing pour un Chat.
 *
 * @author	Xavier CrÃ©gut <Prenom.Nom@enseeiht.fr>
 */
public class ChatSwing2 extends JFrame {

	public ChatSwing2(final Chat chat, String pseudo) {
		super("Chat de " + pseudo);
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, new VueChatSwing(chat, 15, 20));
		this.add(BorderLayout.SOUTH, new ControleurChatSwing(chat, pseudo));

		// Ajouter le bouton Quitter.
		JButton bQuitter = new JButton("Quitter");
		this.add(BorderLayout.NORTH, bQuitter);
		bQuitter.addActionListener( ev -> dispose() );

		// Ne libÃ©rer que cette fenÃªtre si elle est fermÃ©e.
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		pack();
		setVisible(true);
	}

}
