package graphique;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/** Une classe qui charge les images depuis le fichier "res" et les enregistres dans des sprites sets. */
public class SpriteLibrary {
	
	private final static String PATH_TO_UNITS = "/res/sprites/units";

	private static Map<String, SpriteSet> units; // une collection qui associe chaque élèment animés a son SpriteSet.
	
	//Constructeur de la classe.
	public SpriteLibrary() {
		units = new HashMap<>();
		loadSpritesFromDisk();
	}
	
	//On ajoute les SpriteSheets contenues dans chaque fichier contenus dans le path courant a units.
	private void loadSpritesFromDisk() {
		String[] folderNames = getFolderNames(PATH_TO_UNITS);	//Récupérer les dossiers dans le path courant.
		
		for(String folderName: folderNames) {
			String PathToFolder = PATH_TO_UNITS + "/" + folderName;
			SpriteSet spriteSet = new SpriteSet();
			String[] sheetsInFolder = getSheetsInFolder(PathToFolder);//Récupérer toutes les spritesSheets dans le Path "PathToFolder".
			
			for(String sheetName: sheetsInFolder) {
				spriteSet.addsheet(
						sheetName.substring(0, sheetName.length() - 4),
						ImageUtils.loadImage(PathToFolder + "/" + sheetName));//Ajouter chaque SpriteSheet a notre SpriteSet.
			}
			units.put(folderName, spriteSet);
		}
	}
	//Récupérer toutes les SpriteSheets dans le path courant .
	private String[] getSheetsInFolder(String basePath) {
		URL resource = SpriteLibrary.class.getResource(basePath);
		File file = new File(resource.getFile());
		return file.list((current, name) -> new File(current, name).isFile());
	}
	
	//Récupérer tous les dossiers dans le path courant .
	private String[] getFolderNames(String basePath) {
		URL resource = SpriteLibrary.class.getResource(basePath);
		File file = new File(resource.getFile());//Cette fonction peut récuperer des dossiers mais aussi des fichiers.
		return file.list((current, name) -> new File(current, name).isDirectory());//On filtre pour avoir seulement les fichiers.
	}
	//Récupérer le SpriteSet d'un objet animé.
	public static SpriteSet GetUnit(String name) {
		return units.get(name);
	}
}
