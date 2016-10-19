package eg.edu.alexu.csd.oop.CircusOfPlates.observer;

import java.util.List;
import java.util.Observable;

import eg.edu.alexu.csd.oop.CircusOfPlates.object.ShapeObject;
import eg.edu.alexu.csd.oop.game.GameObject;

public class PlateMatcher extends Observable{

	public void notifyObservers(Score score){
		
		score.update(this, 1);
	}
	public boolean check(List<GameObject> stk, Score score){
		int last = stk.size()-1;
		try{
			if( ((ShapeObject)stk.get(last)).getColor().equals(((ShapeObject)stk.get(last-1)).getColor())
					&& ((ShapeObject)stk.get(last)).getColor().equals(((ShapeObject)stk.get(last-2)).getColor())){
				notifyObservers(score);
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}
}
