package eg.edu.alexu.csd.oop.CircusOfPlates.main;

import eg.edu.alexu.csd.oop.CircusOfPlates.strategy.Difficulty;
import eg.edu.alexu.csd.oop.CircusOfPlates.world.Circus;

public class SmallFactory {

	private int index;
	private Circus circus;

	public SmallFactory(int index, Circus circus) {
		this.index = index;
		this.circus = circus;
	}

	public Difficulty getClassObject() {
		ClassFinder c = ClassFinder.getInstance();
		Difficulty d = null;
		try {
			d = c.find().get(this.index).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		d.build(this.circus, this.circus.getMaxTime());
		return d;
	}
}
