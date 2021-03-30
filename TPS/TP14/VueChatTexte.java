/**
 * Définition d'une vue textuelle sur un chat.
 */

public class VueChatTexte {

	public VueChatTexte(Chat chat) {
		// Afficher les message déjà présents
		for (Message m : chat) {
			System.out.println(m);
		}

		// S'inscrire pour afficher les futurs messages
		chat.addObserver( (c, m) -> System.out.println(m));
	}

}
