package eg.edu.alexu.csd.oop.CircusOfPlates.PlateState;

import java.util.List;
import java.util.Observable;
import eg.edu.alexu.csd.oop.CircusOfPlates.ShapeContainer.ShapePool;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.ShapeObject;
import eg.edu.alexu.csd.oop.CircusOfPlates.observer.PlateMatcher;
import eg.edu.alexu.csd.oop.CircusOfPlates.observer.Score;
import eg.edu.alexu.csd.oop.game.GameObject;

public class InStackState implements State{

	private List<GameObject> control;
	private Score score;
	private List<GameObject> stack;
	private ShapePool shapePool = ShapePool.getInstance();
	
	public InStackState(List<GameObject> list, Score score){
		this.control = list;
		this.score = score;
	}
	
	@Override
	public void addPlate(GameObject o) {
		// TODO Auto-generated method stub
		control.add(o);
		performTransition(o);
	}

	@Override
	public List<GameObject> getList() {
		// TODO Auto-generated method stub
		return this.control;
	}

	public void setStack(List<GameObject> stk){
		this.stack = stk;
	}

	@Override
	public void performTransition(GameObject o) {
		// TODO Auto-generated method stub
		Observable matcher = new PlateMatcher();
		if(((PlateMatcher)matcher).check(this.stack, this.score)){
			takeAStep(o);
		}
	}

	@Override
	public void takeAStep(GameObject o) {
		// TODO Auto-generated method stub
		for(int i=0; i<3; i++){
			this.control.remove(stack.get(stack.size()-1));
			shapePool.addShape((ShapeObject)this.stack.remove(stack.size()-1));
		}
	}
}
