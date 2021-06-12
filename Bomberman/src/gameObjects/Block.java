package gameObjects;

//Classe d'un bloc generique.
public interface Block {
	
	/** Modifie le bloc en question, detruit un bloc destructible, pousse un bloc poussable 
	 * (depend du type de bloc qu'on implementera). */
	void modifier();
	
}
