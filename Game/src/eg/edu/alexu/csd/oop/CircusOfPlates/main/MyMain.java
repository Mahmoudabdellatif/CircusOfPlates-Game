package eg.edu.alexu.csd.oop.CircusOfPlates.main;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

public class MyMain {
	
	private static Logger log = Logger.getLogger(MyMain.class);
	
	public static void main(String[] args) {
		
		log.info("game started");
		LevelChooser level = new LevelChooser();
		level.setVisible(true);
		level.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		level.setSize(500, 500);
		level.setResizable(false);
	}

}
