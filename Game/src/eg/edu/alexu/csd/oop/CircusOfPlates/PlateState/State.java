package eg.edu.alexu.csd.oop.CircusOfPlates.PlateState;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface State {
	
	public void addPlate(GameObject o);
	
	public void performTransition(GameObject o);
	
	public void takeAStep(GameObject o);
	
	public List<GameObject> getList();
	
}
