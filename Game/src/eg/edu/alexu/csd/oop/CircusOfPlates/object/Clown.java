package eg.edu.alexu.csd.oop.CircusOfPlates.object;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.CircusOfPlates.strategy.Difficulty;
import eg.edu.alexu.csd.oop.game.GameObject;

public class Clown extends ImageObject{
	
	private List<GameObject> leftStack = new LinkedList<GameObject>();
	private List<GameObject> rightStack = new LinkedList<GameObject>();
	
	public Clown(int posX, int posY, boolean horizontalOnly){
		this.x = posX;
		this.y = posY;
		this.horizontalOnly = horizontalOnly;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
			spriteImages[0] = ImageIO.read(getClass().getResourceAsStream("/res/clown3.png"));
			BarObject leftBar = new BarObject(getX()-15, getY(), 50, true, Color.BLUE);
			BarObject wrightBar = new BarObject(getX()+130, getY(), 50, true, Color.BLUE);
			this.leftStack.add(leftBar);
			this.rightStack.add(wrightBar);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addPlateToLeftStk(GameObject o){
		this.leftStack.add(o);
	}
	public void addPlateToRightStk(GameObject o){
		this.rightStack.add(o);
	}
	
	public List<GameObject> getLeftStk(){
		return this.leftStack;
	}
	public List<GameObject> getRightStk(){
		return this.rightStack;
	}
}