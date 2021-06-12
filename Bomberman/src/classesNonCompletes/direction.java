package classesNonCompletes;

import physique.Mouvement;

public enum direction {
	sud( 0),
	ouest(2),
	est(6),
	nord(4);
	
	
	
	private double line;
	direction(double line) {
		this.line = line;
	}
	
	public static direction Motion(Mouvement motion) {
		double x = motion.getVecteur().getX();
		double y = motion.getVecteur().getY();
		if(x==0 && y>0) return sud;
		if(x<0 && y == 0 ) return ouest;
		if(x==0 && y<0) return nord;
		if(x>0 && y == 0) return est;
		return sud;
	}
	public double getline() {
		return line;
	}
}
