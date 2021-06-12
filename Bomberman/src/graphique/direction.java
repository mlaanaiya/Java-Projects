package graphique;

import physique.Mouvement;

public enum direction {
	N(0),// La direction sud (ligne 0). 
	E(1), // La direction ouest (ligne 1).
	S(2), // La direction Est(ligne 2).
	W(3); //La direction nord(ligne 3).
	
	
	private int line;
	
	//Constructeur de la classe.
	direction(int line) {
		this.line = line;
	}
 	
	//Determiner la direction du joueur.
	public static direction fromMotion(Mouvement motion) {
		double x = motion.getVecteur().getX();
		double y = motion.getVecteur().getY();
		if(x==0 && y>0) return S;
		if(x<0 && y == 0) return W;
		if(x>0 && y==0) return E;
		if(x==0 && y<0) return N;
		if(x>0 && y > 0) return S;
		if(x>0 && y<0) return N;
		if(x<0 && y>0) return S;
		if(x<0 && y<0) return N;
		return S;
	}
	//Récupérer la ligne
	public int getline() {
		return this.line;
	}
}
