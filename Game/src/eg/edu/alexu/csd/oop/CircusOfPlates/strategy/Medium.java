package eg.edu.alexu.csd.oop.CircusOfPlates.strategy;

import java.awt.Color;
import eg.edu.alexu.csd.oop.CircusOfPlates.ShapeContainer.ShapePool;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.BarObject;
import eg.edu.alexu.csd.oop.CircusOfPlates.world.Circus;

public class Medium implements Difficulty{

	private long maxTime;
	private Circus circus;
	private ShapePool shapePool = ShapePool.getInstance();
	public void build(Circus circus, long maxTime) {
		this.circus = circus;
		this.maxTime = maxTime/1000;
	}
	private long millisSinceGMTMidnight = System.currentTimeMillis() ;
	
	@Override
	public void startTimer(long time, long time1){
		if(System.currentTimeMillis() - time > 3000) {
			circus.setTime(System.currentTimeMillis());
	        circus.getMovingRightState().addPlate(shapePool.getShape(0,10,false,500));
	        circus.getMovingRightState().addPlate(shapePool.getShape(0,40,false,300));
	    }
		if(System.currentTimeMillis() - time1 > 2000) {
			circus.setTime1(System.currentTimeMillis());
			circus.getMovingLeftState().addPlate(shapePool.getShape(circus.getWidth()-100,10,false,1362-500));
			circus.getMovingLeftState().addPlate(shapePool.getShape(circus.getWidth()-100,40,false,1362-300));
		}
	}
	@Override
	public boolean breakTimer(long time){
		return !((time/1000 - this.millisSinceGMTMidnight/1000) >= this.maxTime);
	}
	@Override
	public void adjustLevelDifficulty(Circus circus) {
		Easy e = new Easy();
		e.build(this.circus, this.maxTime);
		e.adjustLevelDifficulty(this.circus);
		circus.getConstant().add(new BarObject(0,50,300,false,Color.BLUE));
		circus.getConstant().add(new BarObject(1362-300,50,300,false,Color.BLUE));
	}

}
