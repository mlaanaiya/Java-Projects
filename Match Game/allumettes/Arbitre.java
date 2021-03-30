package allumettes;
import java.util.InputMismatchException;

/** La classe Arbitre fait jouer, à tour de
 * rôle, chaque joueur en commençant par le joueur j1
 * en respectant les règles du jeu.
 *
 * @author	Laanaiya Mahmoud
 * @version	2.0
 */



public class Arbitre {
	/** Premier Joueur.*/
	private Joueur j1;
	/** Dexieme Joueur.*/
	private Joueur j2;
	/** Mode de l'arbitrage.*/
	private boolean confiant;

    	/** Construire un arbitre à partir de deux joueurs.
     	* @param j1 premier joueur
     	* @param j2 deuxième joueur
     	*/
	public Arbitre(Joueur j1, Joueur j2) {
		assert (j1 != null && j2 != null);
		this.j1 = j1;
		this.j2 = j2;
	}

    	/** Construire un arbitre à partir de deux joueurs et
	 * de son mode.
     	* @param j1 premier joueur
     	* @param j2 deuxième joueur
	* @param confiant mode
     	*/
	public Arbitre(Joueur j1, Joueur j2, boolean confiant) {
		this(j1, j2);
		this.confiant = confiant;
	}


    	/** Obtenir le premier joueur.
     	* @return j1 le premier joueur
     	*/
	public Joueur getJ1() {
		return this.j1;
	}

    	/** Obtenir le deuxième joueur.
     	* @return j1 le deuxième joueur
     	*/
	public Joueur getJ2() {
		return this.j2;
	}

    	/** Obtenir le joueur actuel.
	* @param tour le tour du jeu
     	* @return JoueurActuel joueur actuel
     	*/
	public Joueur getJoueurActuel(int tour) {
		assert (tour > 0);
		if (tour % 2 == 0) {
			return this.j2;
		} else {
			return this.j1;
		}
	}

    	/** Changer le premier joueur.
     	* @param j1 le nouveau joueur
     	*/
	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

    	/** Changer le deuxième joueur.
     	* @param j2 le nouveau joueur
     	*/
	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}

    	/** Savoir si le jeu est fini.
     	* @param tour le tour en jeu
	* @param jeu le jeu
	* @return condition de jeu fini.
     	*/
	public boolean jeuFini(int tour, Jeu jeu) {
		assert (tour > 1 && jeu != null);
		if (jeu.getNombreAllumettes() == 0) {
			System.out.println(getJoueurActuel(tour - 1).getNom() + " perd !");
			System.out.println(getJoueurActuel(tour).getNom() + " gagne !");
			return true;

		} else {
			return false;
		}
	}

	/** Arbitrer le jeu.
	 * @param jeu
	 */
    	public void arbitrer(Jeu jeu) {
        	assert (jeu != null);
		Jeu proxyjeu = new JeuMandataire(jeu);
        	int tour = 1;
        	int tmp;
        	do {
			System.out.print("Nombre d'allumettes restantes : ");
			System.out.println(jeu.getNombreAllumettes() + "\t");
			 try {

				tmp = getJoueurActuel(tour).getPrise(proxyjeu);
				System.out.print(getJoueurActuel(tour).getNom());
               			if (tmp == 1 || tmp == 0 || tmp == -1) {
					System.out.println(" prend " + tmp + " allumette." + "\t");
				} else {
					System.out.println(" prend " + tmp + " allumettes." + "\t");
				}
               			jeu.retirer(tmp);
				tour += 1;

            		} catch (CoupInvalideException e) {
                		System.out.println("Impossible ! " + e.getMessage());

            		} catch (OperationInterditeException e) {
				if (!this.confiant) {
					System.out.println(e.getMessage());
                			System.out.print("Abandon de la partie car ");
					System.out.print(getJoueurActuel(tour).getNom());
					System.out.println(" triche !" + "\t");
	                		break;
				} else {
					if (getJoueurActuel(tour).getStrategie().getNom() == "tricheur") {
						System.out.println(e.getMessage());
						System.out.println("[Allumettes restantes : 2]");
						((JeuAllumettes) jeu).setNombreAllumettes(2);
						System.out.print(getJoueurActuel(tour).getNom());
						System.out.println(" prend 1 allumette." + "\t");
						((JeuAllumettes) jeu).setNombreAllumettes(1);
						tour += 1;
					} else {
						System.out.print(getJoueurActuel(tour).getNom());
						System.out.print(", combien d'allumettes ? ");

						try {
							int a = Strategie.SCANNER.nextInt();
							System.out.print(getJoueurActuel(tour).getNom());
							System.out.println(" prend " + a + " allumettes." + "\t");
							int nouveaunombre = jeu.getNombreAllumettes() - a - 1;
							((JeuAllumettes) jeu).setNombreAllumettes(nouveaunombre);
							tour += 1;
						} catch (InputMismatchException f) {
							System.out.println("Vous devez donner un entier.");
						}

					}

				}
           		}
        	} while (!(jeuFini(tour, jeu)));
    	}

}


