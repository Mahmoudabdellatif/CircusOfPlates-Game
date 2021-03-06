package eg.edu.alexu.csd.oop.CircusOfPlates.strategy;

import java.awt.Color;
import eg.edu.alexu.csd.oop.CircusOfPlates.ShapeContainer.ShapePool;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.BarObject;
import eg.edu.alexu.csd.oop.CircusOfPlates.world.Circus;

public class Easy implements Difficulty{

	private long maxTime;
	private Circus circus;
	private ShapePool shapePool = ShapePool.getInstance();
	private long millisSinceGMTMidnight = System.currentTimeMillis() ;
	
	@Override
	public void build(Circus circus, long maxTime){
		this.circus = circus;
		this.maxTime = maxTime/1000;
	}

	@Override
	public void startTimer(long time, long time1){
		if(System.currentTimeMillis() - time > 3000){
			circus.setTime(System.currentTimeMillis());
	        circus.getMovingRightState().addPlate(shapePool.getShape(0,10,false,500));
		}
		if(System.currentTimeMillis() - time1 > 2000){
			circus.setTime1(System.currentTimeMillis());
	        circus.getMovingLeftState().addPlate(shapePool.getShape(circus.getWidth()-100, 10, false, 1362-500));
		}
	}
	
	@Override
	public boolean breakTimer(long time){
		return !((time/1000 - this.millisSinceGMTMidnight/1000) >= this.maxTime);
	}
	
	@Override
	public void adjustLevelDifficulty(Circus circus) {
		circus.getConstant().add(new BarObject(0,20,500,false,Color.BLACK));
		circus.getConstant().add(new BarObject(1362-500,20,500,false,Color.BLACK));
	}
	
}
