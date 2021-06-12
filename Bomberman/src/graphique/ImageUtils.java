package graphique;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;


//Charger une image depuis un fichier .
public class ImageUtils {

	public static Image loadImage(String filePath) {
		try {
			return ImageIO.read(ImageUtils.class.getResource(filePath));
		} catch (IOException e ) {
			e.printStackTrace();
		}
		return null;
		
	}
}


