package eg.edu.alexu.csd.oop.CircusOfPlates.object;

import java.awt.Color;
import java.awt.image.BufferedImage;
import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class ImageObject implements GameObject{
	private final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	protected BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	protected int x, y;
	protected boolean visible = true, horizontalOnly = false;
	protected Color color;
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int mY) {
		if(horizontalOnly)
			return;
		this.y = mY;
	}
	
	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public void setHorizontalOnly(boolean arg){
		this.horizontalOnly = arg;
	}
	
	public Color getColor(){
		return this.color;
	}
}