package eg.edu.alexu.csd.oop.CircusOfPlates.PlateState;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.CircusOfPlates.iterator.ListIterator;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.ShapeObject;
import eg.edu.alexu.csd.oop.game.GameObject;

public class MovingLeftState implements State, ListIterator{

	private final List<GameObject> movingHerL;
	private State nextState;
	
	public MovingLeftState(State newState){
		movingHerL = new LinkedList<GameObject>();
		nextState = newState;
	}
	
	@Override
	public void addPlate(GameObject o) {
		movingHerL.add(o);
	}
	
	@Override
	public void performTransition(GameObject o) {
		if(o.getX()==((ShapeObject)o).getBarWidth()){
			nextState.addPlate(o);
			movingHerL.remove(o);
		}else {
			takeAStep(o);
		}
	}

	@Override
	public void takeAStep(GameObject o) {
		o.setX((o.getX() - 1));
	}

	@Override
	public List<GameObject> getList() {
		return movingHerL;
	}

	@Override
	public Iterator createIterator() {
		return this.movingHerL.iterator();
	}

}
