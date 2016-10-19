package eg.edu.alexu.csd.oop.CircusOfPlates.object;

import java.awt.Color;
import java.awt.image.BufferedImage;
public class ShapeObject extends ImageObject {
	
	private int barWidth;
	private String imageName;
	public ShapeObject(int x, int y, boolean horizontalOnly, int barWidth, Color color, BufferedImage image, String imageName) {
		this.barWidth = barWidth;
		this.x = x;
		this.y = y;
		this.color = color;
		this.horizontalOnly = horizontalOnly;
		this.imageName = imageName;
		// create a bunch of buffered images and place into an array, to be
		// displayed sequentially
		spriteImages[0] = image;
	
	}

	public String getImageName(){
		return this.imageName;
	}
	
	public int getBarWidth() {
		return this.barWidth;
	}
	
	public void setParameters(int x, int y, boolean horizontalOnly, int barWidth) {
		this.x = x;
		this.y = y;
		this.horizontalOnly = horizontalOnly;
		this.barWidth = barWidth;
	}

}
