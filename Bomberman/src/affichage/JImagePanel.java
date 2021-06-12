package affichage;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
 
public class JImagePanel extends JPanel {
 
    public Image image = null;
    public Boolean stretch = true;
 
    /**
     *
     */
    public JImagePanel(Image image) {
        this.image = image;
    }
 
    /**
     * 
     */
    public JImagePanel(String file) {
        this.image = getToolkit().getImage(file);
    }
 
   /**
    */
    public void setStretch(Boolean stretch) {
        this.stretch = stretch;
    }
    
    public void paintComponent(Graphics g) {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
 
        if (this.stretch) {
            width = this.getWidth();
            height = this.getHeight();
        } else {
            width = this.image.getWidth(this);
            height = this.image.getHeight(this);
            //x=((this.getWidth()-width)/2);
            //y=((this.getHeight()-height)/2);
	    x = 370;
	    this.setBackground(Color.black);
        }  
        g.drawImage(this.image, x, y, width, height,Color.pink, this);
    }
}
