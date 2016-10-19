package eg.edu.alexu.csd.oop.CircusOfPlates.PlateState;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.CircusOfPlates.ShapeContainer.ShapePool;
import eg.edu.alexu.csd.oop.CircusOfPlates.iterator.ListIterator;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.Clown;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.ShapeObject;
import eg.edu.alexu.csd.oop.game.GameObject;

public class MovingVerticalState implements State, ListIterator{

	private final List<GameObject> movingVer;
	private State nextState;
	private Clown clown;
	private int height;
	private ShapePool shapePool = ShapePool.getInstance();
	private static Logger log = Logger.getLogger(MovingVerticalState.class);
	
	public MovingVerticalState(State newState, int height){
		
		this.movingVer = new LinkedList<GameObject>();
		this.nextState = newState;
		this.height = height;
	}
	
	public void setClown(Clown c){
		this.clown = c;
	}

	@Override
	public void addPlate(GameObject o) {
		movingVer.add(o);
	}
	
	@Override
	public void takeAStep(GameObject o) {
		o.setY((o.getY() + 1));
		if(o.getY() >= height){
			movingVer.remove(o);
			shapePool.addShape((ShapeObject)o);
			//remove from moving list 
		}
	}

	@Override
	public List<GameObject> getList() {
		return movingVer;
	}

	public boolean intersect(GameObject o1, GameObject o2){
		return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) 
				&& (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
	}

	@Override
	public Iterator createIterator() {
		return movingVer.iterator();
	}

	@Override
	public void performTransition(GameObject o) {
		//if intersected with first plate on the left stack
		if(intersect(o, clown.getLeftStk().get(clown.getLeftStk().size()-1) ) && o.isVisible()){
			((ShapeObject)o).setParameters(clown.getLeftStk().get(clown.getLeftStk().size()-1).getX(), clown.getLeftStk().get(clown.getLeftStk().size()-1).getY() - 10, true, ((ShapeObject)o).getBarWidth());
			movingVer.remove(o);
			clown.addPlateToLeftStk(o);
			((InStackState)nextState).setStack(clown.getLeftStk());
			nextState.addPlate(o);
			log.info("shape in stack");
		}

		//if intersected with first plate on right the stack		
		else if(intersect(o, clown.getRightStk().get(clown.getRightStk().size()-1)) && o.isVisible()){
			((ShapeObject)o).setParameters(clown.getRightStk().get(clown.getRightStk().size()-1).getX(), clown.getRightStk().get(clown.getRightStk().size()-1).getY() - 10, true, ((ShapeObject)o).getBarWidth()); 
			movingVer.remove(o);
			clown.addPlateToRightStk(o);
			((InStackState)nextState).setStack(clown.getRightStk());
			nextState.addPlate(o);
			log.info("shape in stack");
		}
		else {
			takeAStep(o);
		}
	}

}
