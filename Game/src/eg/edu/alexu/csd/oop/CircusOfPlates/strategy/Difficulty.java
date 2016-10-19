package eg.edu.alexu.csd.oop.CircusOfPlates.strategy;

import eg.edu.alexu.csd.oop.CircusOfPlates.world.Circus;

public interface Difficulty {

	public void build(Circus circus, long maxTime);

	public void adjustLevelDifficulty(Circus circus);

	public void startTimer(long time, long time1);

	public boolean breakTimer(long maxTime);

}
