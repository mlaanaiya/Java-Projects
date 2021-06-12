package gameObjects;

import map.Tile;

// Classe d'un bloc destructible.
public class BreakableBlock implements Block {
	
	/** Tuile sur laquelle se trouve le bloc. */
	private Tile tile;
	
	/** Constructeur du bloc destructible.
	 * @param tile, la tuile sur laquelle se situe le bloc.
	 */
	public BreakableBlock(Tile tile) {
		this.tile = tile;
	}
	
	/** Modifie le bloc en question, detruit un bloc destructible, pousse un bloc poussable 
	 * (depend du type de bloc qu'on implementera). */
	@Override
	public void modifier() {
		// TODO Auto-generated method stub
		breakBlock();
	}

	/** Detruit le bloc. et le transforme en tuile normale. */
	public void breakBlock() {
		this.tile.setType(null);
	}
}