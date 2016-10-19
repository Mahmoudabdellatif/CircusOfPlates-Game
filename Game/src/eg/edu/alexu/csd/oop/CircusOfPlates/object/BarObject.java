package eg.edu.alexu.csd.oop.CircusOfPlates.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class BarObject extends ImageObject{
	private final int SPRITE_HEIGHT = 5;
	private int width;
	
	public BarObject(int posX, int posY, int width, boolean horizontalOnly, Color color){
		this.x = posX;
		this.y = posY;
		this.width = width;
		this.horizontalOnly = horizontalOnly;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		spriteImages[0] = new BufferedImage(width, SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = spriteImages[0].createGraphics();
		g2.setColor(color);
		g2.setBackground(color);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(20));
		g2.drawLine(0, 0, getWidth(), 0);
		g2.dispose();
	}

	public int getWidth(){
		return this.width;
	}

	public int getHeight() {
		return SPRITE_HEIGHT;
	}

}