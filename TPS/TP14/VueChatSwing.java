import javax.swing.*;

/**
 * Une vue sur les discussions d'un salon de discussion.
 */

public class VueChatSwing extends JTextArea {

	public VueChatSwing(final Chat chat, int nbLignes, int nbColonnes) {
		super(nbLignes, nbColonnes);
		this.setEditable(false);

		// Afficher les message dÃ©jÃ  prÃ©sents sur le chat
		for (Message m : chat) {
			this.append("" + m + "\n");
		}

		// S'inscrire auprÃ¨s du chat
		chat.addObserver( (c, m) -> this.append("" + m + "\n"));
	}

}
