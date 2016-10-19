package eg.edu.alexu.csd.oop.CircusOfPlates.observer;

import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

public class Score implements Observer {

	private int score = 0;
	private static Logger log = Logger.getLogger(Score.class);
	
	@Override
	public void update(Observable o, Object arg) {
		this.score++;
		log.info("player's score increased: " + score);
	}

	public int getScore() {
		return this.score;
	}
}
