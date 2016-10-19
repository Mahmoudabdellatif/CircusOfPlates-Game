package eg.edu.alexu.csd.oop.CircusOfPlates.ShapeContainer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class ImageFactory {

	private HashMap<String,BufferedImage> imageFlyweight = new HashMap<String, BufferedImage>();
	private Color colors[] = {Color.CYAN, Color.YELLOW, Color.RED, Color.BLUE};
	private Logger log = Logger.getLogger(ImageFactory.class);
	
	public ImageFactory(){
		try {
			imageFlyweight.put("shape1", ImageIO.read(getClass().getResourceAsStream("/res/shape1.png")));
			imageFlyweight.put("shape2", ImageIO.read(getClass().getResourceAsStream("/res/shape2.png")));
			imageFlyweight.put("shape3", ImageIO.read(getClass().getResourceAsStream("/res/shape3.png")));
			imageFlyweight.put("shape4", ImageIO.read(getClass().getResourceAsStream("/res/shape4.png")));
			log.info("shapes images loaded from resources");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(String imageName){
		return imageFlyweight.get(imageName);
	}
	
	public Color getColor(int index){
		return colors[index-1];
	}
}
