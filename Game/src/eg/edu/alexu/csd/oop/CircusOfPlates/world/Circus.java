package eg.edu.alexu.csd.oop.CircusOfPlates.world;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.CircusOfPlates.PlateState.InStackState;
import eg.edu.alexu.csd.oop.CircusOfPlates.PlateState.MovingLeftState;
import eg.edu.alexu.csd.oop.CircusOfPlates.PlateState.MovingRightState;
import eg.edu.alexu.csd.oop.CircusOfPlates.PlateState.MovingVerticalState;
import eg.edu.alexu.csd.oop.CircusOfPlates.PlateState.State;
import eg.edu.alexu.csd.oop.CircusOfPlates.main.SmallFactory;
import eg.edu.alexu.csd.oop.CircusOfPlates.object.Clown;
import eg.edu.alexu.csd.oop.CircusOfPlates.observer.Score;
import eg.edu.alexu.csd.oop.CircusOfPlates.strategy.Difficulty;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

public class Circus implements World {
	private final int MAX_TIME = 1 * 60 * 1000; // 1 minute
	private long startTime = System.currentTimeMillis();
	private final int width, height;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	private State controled, movingVer, movingHerR, movingHerL;
	private Difficulty levelDifficulty;
	private long leftPlateTimer = 0, rightPlateTimer = 0;
	private Score score;

	public Circus(int screenWidth, int screenHeight, int index) {
		score = new Score();
		width = screenWidth;
		height = screenHeight;

		SmallFactory sf = new SmallFactory(index, this);
		levelDifficulty = sf.getClassObject();
		levelDifficulty.adjustLevelDifficulty(this);

		// control objects (clown)
		control.add(new Clown(200, 531, true));
		control.add(new Clown(500, 531, true));
		for (int i = 0; i < 2; i++) {
			Clown clown = (Clown) control.get(i);
			control.add(clown.getLeftStk().get(0));
			control.add(clown.getRightStk().get(0));
		}
		controled = new InStackState(control, score);
		movingVer = new MovingVerticalState(controled, height);
		movingHerR = new MovingRightState(movingVer);
		movingHerL = new MovingLeftState(movingVer);
	}

	@Override
	public boolean refresh() {
		levelDifficulty.startTimer(leftPlateTimer, rightPlateTimer);
		Iterator iterator;
		// moving starts
		try {
			iterator = ((MovingRightState) movingHerR).createIterator();
			while (iterator.hasNext()) {
				movingHerR.performTransition((GameObject) iterator.next());
			}
			iterator = ((MovingLeftState) movingHerL).createIterator();
			while (iterator.hasNext()) {
				movingHerL.performTransition((GameObject) iterator.next());
			}
			for (int i = 0; i < 2; i++) {
				Clown clown = (Clown) control.get(i);
				((MovingVerticalState) movingVer).setClown(clown);
				iterator = ((MovingVerticalState) movingVer).createIterator();
				while (iterator.hasNext()) {
					movingVer.performTransition((GameObject) iterator.next());
				}
			}
		} catch (Exception e) {
		}
		if (score.getScore() == 10)
			return false;
		return levelDifficulty.breakTimer(rightPlateTimer);
	}

	public void setTime(long time) {
		this.leftPlateTimer = time;
	}

	public void setTime1(long time) {
		this.rightPlateTimer = time;
	}

	@Override
	public int getSpeed() {
		return 10;
	}

	@Override
	public int getControlSpeed() {
		return 20;
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		List<GameObject> moving = new LinkedList<GameObject>();
		moving.addAll(movingHerR.getList());
		moving.addAll(movingHerL.getList());
		moving.addAll(movingVer.getList());
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return controled.getList();
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getStatus() {
		String status = "";
		if (score.getScore() >= 10)
			status = " you win";
		return "Score=" + score.getScore() + status + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000); // update
																								// status
	}

	public State getMovingRightState() {
		return movingHerR;
	}

	public State getMovingLeftState() {
		return movingHerL;
	}

	public State getMovingVerState() {
		return movingVer;
	}

	public List<GameObject> getConstant() {
		return this.constant;
	}

	public long getMaxTime() {
		return this.MAX_TIME;
	}

}