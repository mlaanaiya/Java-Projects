package graphique;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
/** Une classe pour former des images matricielles pour animer les objets graphiques.*/
public class SpriteSet {
	private Map<String, Image> animationSheets; //Une collction qui associe chaque nom a une image.
	//Constructeur de classe.
	public SpriteSet() {
		this.animationSheets = new HashMap<>();
	}
	//Ajouter de nouvelles images.
	public void addsheet(String name, Image animationSheet) {
		animationSheets.put(name, animationSheet);
	}
	//Recupérer une image d'aprés son nom.
	public Image get(String name ) {
		return animationSheets.get(name);
	}
}
