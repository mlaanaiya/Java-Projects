package allumettes;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author	Laanaiya Mahmoud
 * @version	$Revision: 1.5 $
 */
public final class Jouer {

	/** Constructeur inutile.*/
	private Jouer() {
      		// non appellé
   	}

	/** Le nombre d'allumettes qu'on va utiliser.*/
	public static final int NBALLUMETTES = 13;

    	/** Saisir la stratégie du joueur.
     	 * @param str le caractère en argument
     	 * @return Strategie
     	 */

	public static Strategie getStrategie(String str) throws ConfigurationException {
	        if (str.equals("naif")) {
	            return new StrategieNaive();
	        } else if (str.equals("rapide")) {
	            return new StrategieRapide();
	        } else if (str.equals("expert")) {
	            return new StrategieExpert();
	        } else if (str.equals("humain")) {
	            return new StrategieHumain();
	        } else if (str.equals("tricheur")) {
	            return new StrategieTricheur();
	        } else if (str.equals("lent")) {
	            return new StrategieLente();
	        } else {
	            throw new ConfigurationException("Stratégie introuvable");
	        }
	}

    	/** Transformer les arguments dans le terminal
	 * en String.
     	 * @param args Arguments
     	 * @return argument en string
     	 */
	public static String argsToString(String[] args) {
		String str = "";
		int i = args.length - 1;
		while (i >= 0) {
			if (i > 0) {
				str += args[i] + "@";
			} else {
				str += args[i];
			}
			i--;
		}
		return str;
	}
	/** Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
    	public static void main(String[] args) {
		try {
			verifierNombreArguments(args);
			Joueur joueur1;
			Joueur joueur2;
			boolean confiant;
			String spl = argsToString(args);
			String[] tmp = spl.split("@");
			joueur1 = new Joueur(tmp[2], getStrategie(tmp[Jeu.PRISE_MAX]));
			joueur2 = new Joueur(tmp[0], getStrategie(tmp[1]));
			if (args.length == 2) {
				confiant = false;
			} else {
				if (args[0].equals("-confiant") && args.length == Jeu.PRISE_MAX) {
					confiant = true;
				} else {
					throw new ConfigurationException("Argument incorrect");
				}
			}
           		JeuAllumettes jeu = new JeuAllumettes(NBALLUMETTES);
           		Arbitre arbitre = new Arbitre(joueur1, joueur2, confiant);
            		arbitre.arbitrer(jeu);
		} catch (ArrayIndexOutOfBoundsException e) {
            		System.out.println("Usage : NOM@STRATEGIE");
            		System.out.println("Erreur : Arguments introuvables");

		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}

    	}

	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}

}
