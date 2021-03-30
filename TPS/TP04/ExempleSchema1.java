import afficheur.AfficheurSVG;
import afficheur.Ecran;

/** Construire le schéma proposé dans le sujet de TP avec des points,
  * et des segments.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.7 $
  */
public class ExempleSchema1 {

	/** Construire le schéma et le manipuler.
	  * Le schéma est affiché.
	  * Ensuite, il est translaté et affiché de nouveau.
	  * Les arguments permettent de définir le titre (-T) et si
	  * l'affichage se fait sur deux écrans (-2) ou un seul (-1,
	  * défaut).
	  * @param args les arguments de la ligne de commande
	  */
	public static void main(String[] args)
	{
		// Déclarations des options
		boolean dual = false;		// faut-il deux écrans ?
		String titre = "Afficheur";	// le titre de la fenêtre
		int largeur = 400;
		int hauteur = 250;
		int unite = 15;

		// Analyser les arguments
		for (int i = 0; i < args.length; i++) {
			String option = args[i];
			if (option.equals("-h")) {
				System.out.println();
				System.out.println("Les options sont :");
				System.out.println("	-h : cette aide");
				System.out.println("	-1 : un seul afficheur");
				System.out.println("	-2 : deux afficheurs");
				System.out.println("	-Ttitre : titre des afficheurs");
				System.out.println();
				System.exit(0);
			} else if (option.equals("-1")) {
				dual = false;
			} else if (option.equals("-2")) {
				dual = true;
			} else if (option.startsWith("-T")) {
				titre = option.substring(2);
			} else {
				System.out.println("Option ou paramètre inconnu : " + option);
			}
		}

		// Créer les trois segments
		Point p1 = new Point(3, 2);
		Point p2 = new Point(6, 9);
		Point p3 = new Point(11, 4);
		Segment s12 = new Segment(p1, p2);
		Segment s23 = new Segment(p2, p3);
		Segment s31 = new Segment(p3, p1);

		// Créer le barycentre
		double sx = p1.getX() + p2.getX() + p3.getX();
		double sy = p1.getY() + p2.getY() + p3.getY();
		Point barycentre = new Point(sx / 3, sy / 3);

		// Afficher le schéma
		System.out.println("Le schéma est composé de : ");
		s12.afficher();		System.out.println();
		s23.afficher();		System.out.println();
		s31.afficher();		System.out.println();
		barycentre.afficher();	System.out.println();
		// Créer l'écran d'affichage
		String titreAvant = titre + ((dual) ? " -- avant translater(4, -3)" : "");
		Ecran ecran = new Ecran(titreAvant, largeur, hauteur, unite);
		ecran.dessinerAxes();

		// Dessiner le schéma sur l'écran graphique
		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);
		barycentre.dessiner(ecran);

		// Afficher le schema en SVG
		AfficheurSVG enSVG = new AfficheurSVG();
		s12.dessiner(enSVG);
		s23.dessiner(enSVG);
		s31.dessiner(enSVG);
		barycentre.dessiner(enSVG);
		enSVG.ecrire();
		enSVG.ecrire("schema.svg");

		// Afficher le schema en texte lisible
		AfficheurTexte afficheurTexte = new AfficheurTexte();
		s12.dessiner(afficheurTexte);
		s23.dessiner(afficheurTexte);
		s31.dessiner(afficheurTexte);
		barycentre.dessiner(afficheurTexte);

		// Translater le schéma
		System.out.println("Translater le schéma de (4, -3) : ");
		s12.translater(4, -3);
		s23.translater(4, -3);
		s31.translater(4, -3);
		barycentre.translater(4, -3);

		// Afficher le schéma
		System.out.println("Le schéma est composé de : ");
		s12.afficher();		System.out.println();
		s23.afficher();		System.out.println();
		s31.afficher();		System.out.println();
		barycentre.afficher();	System.out.println();

		if (dual) {
			// Créer un autre écran d'affichage
			String titreApres = titre + ((dual) ? " -- après translater(4, -3)" : "");
			ecran = new Ecran(titreApres, largeur, hauteur, unite, largeur+20, 0);
			ecran.dessinerAxes();
			enSVG = new AfficheurSVG();
		}

		// Dessiner le schéma sur l'écran graphique
		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);
		barycentre.dessiner(ecran);

		// Afficher le schéma en SVG
		s12.dessiner(enSVG);
		s23.dessiner(enSVG);
		s31.dessiner(enSVG);
		barycentre.dessiner(enSVG);
		enSVG.ecrire();
		enSVG.ecrire("schema2.svg");

		// Afficher le schéma en texte lisible
		s12.dessiner(afficheurTexte);
		s23.dessiner(afficheurTexte);
		s31.dessiner(afficheurTexte);
		barycentre.dessiner(afficheurTexte);

		// Forcer l'affichage du schéma (au cas où...)
		ecran.rafraichir();
	}

}
