package eg.edu.alexu.csd.oop.CircusOfPlates.ShapeContainer;

import java.util.Random;

import eg.edu.alexu.csd.oop.CircusOfPlates.object.ShapeObject;

public class ShapeFactory {

	private static ShapeFactory SHAPESFACTORY;
	private ImageFactory imgFct = new ImageFactory();
	private ShapeFactory(){}
	public static ShapeFactory getInstance(){
		if(SHAPESFACTORY == null){
			SHAPESFACTORY = new ShapeFactory(); 
		}
		return SHAPESFACTORY;
	}
	
	public ShapeObject getShape (int x, int y, boolean horizontalOnly, int barWidth){
		String name = imageNameRandGenerator();
		return new ShapeObject(x, y, horizontalOnly, barWidth, imgFct.getColor(Character.getNumericValue(name.charAt(5))), imgFct.getImage(name), name);
	}
	
	public String imageNameRandGenerator() {
		Random Rand = new Random();
		int i = Rand.nextInt(4)+1;
		return "shape"+i;
	}
	
}
