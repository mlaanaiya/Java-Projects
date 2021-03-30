/**
 * {@code ExempleChat} crée un exemple de chat avec plusieurs participants.
 *
 * @author Christophe Garion
 * @author Xavier Crégut
 */
public class ExempleChat {

	public static void main(String[] args) throws InterruptedException {
		// Corriger args si vide
		if (args.length == 0) {
			args = new String[] { "Moi", "Toi", "Lui" };
		}

		// Créer le modèle (salon de discussion)
		Chat c = new Chat();

		// Construire une vue pour chaque élément de args
		int x = 10;
		int y = 10;
		for (String nom: args) {
			new ChatSwing2(c, nom).setLocation(x, y);
			java.util.concurrent.TimeUnit.MILLISECONDS.sleep(100);
			x += 150;
			y += 40;
		}
	}

}
