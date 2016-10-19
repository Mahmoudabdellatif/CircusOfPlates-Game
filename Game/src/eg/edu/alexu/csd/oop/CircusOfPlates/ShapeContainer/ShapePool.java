package eg.edu.alexu.csd.oop.CircusOfPlates.ShapeContainer;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.CircusOfPlates.main.ClassFinder;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.ShapeObject;

public class ShapePool {
	private LinkedList<ShapeObject> pool = new LinkedList<ShapeObject>();
	private Logger log = Logger.getLogger(ShapePool.class);
	ShapeFactory shapeFactory = ShapeFactory.getInstance();
	
	private static ShapePool SHAPESPOOL;
	public static ShapePool getInstance(){
		if(SHAPESPOOL == null){
			SHAPESPOOL = new ShapePool();
		}
		return SHAPESPOOL;
	}
		
	//needs to check for existence of object with such a state ... if not exist ... get one from factory
	public ShapeObject getShape(int x, int y, boolean horizontalOnly, int barWidth){
	
		ShapeObject p = null;
		int index = exist(shapeFactory.imageNameRandGenerator());
		log.info("new shape");
		
		if(pool.size()>0 && index != -1){
			
			p = pool.get(index);
			pool.remove(index);
			p.setParameters(x, y, horizontalOnly, barWidth);
			log.debug("loaded image from pool");
		}
		else{
			p = shapeFactory.getShape(x, y, horizontalOnly, barWidth);
			log.debug("loaded image from factory");
		}
		return p;
	}
	
	private int exist(String imageName) {
		int size = pool.size();
		for(int i=0;i<size;i++){
			ShapeObject temp = pool.get(i);
			if(temp.getImageName().equals(imageName)){
				return i;
			}
		}
		return -1;
	}

	public void addShape(ShapeObject p){
		pool.add(p);
	}	
		
}