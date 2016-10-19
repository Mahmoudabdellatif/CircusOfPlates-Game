package eg.edu.alexu.csd.oop.CircusOfPlates.PlateState;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.CircusOfPlates.iterator.ListIterator;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.ShapeObject;
import eg.edu.alexu.csd.oop.game.GameObject;

public class MovingRightState implements State, ListIterator{

	private final List<GameObject> movingHerR;
	private State nextState;
	
	public MovingRightState(State newState){
		movingHerR = new LinkedList<GameObject>();
		nextState = newState;
	}
	
	@Override
	public void addPlate(GameObject o) {
		movingHerR.add(o);
	}

	@Override
	public void performTransition(GameObject o) {
		if(o.getX() == ((ShapeObject)o).getBarWidth()){
			nextState.addPlate((ShapeObject)o);
			movingHerR.remove((ShapeObject)o);
		}else {
			takeAStep((ShapeObject)o);
		}
	}

	@Override
	public void takeAStep(GameObject o) {
		o.setX((o.getX() + 1));
	}

	@Override
	public List<GameObject> getList() {
		return movingHerR;
	}

	@Override
	public Iterator createIterator() {
		return this.movingHerR.iterator();
	}

}
