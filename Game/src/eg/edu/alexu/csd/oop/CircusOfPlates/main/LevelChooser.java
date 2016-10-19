package eg.edu.alexu.csd.oop.CircusOfPlates.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.CircusOfPlates.world.Circus;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

public class LevelChooser extends JFrame implements ActionListener{
	
	
	private JPanel panel;
	private JMenuBar menu;
	private JMenu levelMenu;
	private Circus circus;
	private ClassFinder c;
	public LevelChooser(){
		
		panel = new JPanel();
		c = ClassFinder.getInstance();
		menu = new JMenuBar();
		levelMenu = new JMenu("Choose Level");
		for(int i = 0 ; i < c.find().size() ; i++){
			levelMenu.add(new JMenuItem(c.find().get(i).getSimpleName()));
			levelMenu.getItem(i).addActionListener(this);
		}
		levelMenu.addActionListener(this);
		menu.add(levelMenu);
		panel.add(menu);
		this.add(panel);
		
	}
	private int i = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(i = 0 ; i < levelMenu.getItemCount() ; i++){
			if(e.getSource().equals(levelMenu.getItem(i))){
				dispose();
				final JMenuBar  menuBar = new JMenuBar();;
				JMenu menu = new JMenu("File");
				JMenuItem pauseMenuItem = new JMenuItem("Pause");
				JMenuItem resumeMenuItem = new JMenuItem("Resume");
				menu.addSeparator();
				menu.add(pauseMenuItem);
				menu.add(resumeMenuItem);
				menu.addSeparator();
				menuBar.add(menu);
				panel.add(menuBar);
				this.circus = new eg.edu.alexu.csd.oop.CircusOfPlates.world.Circus(1362, 800, i);
				final GameController gameController = GameEngine.start("Circus", this.circus, menuBar, Color.WHITE);
				pauseMenuItem.addActionListener(new ActionListener() {
					@Override 
					public void actionPerformed(ActionEvent e) {
							gameController.pause();
						}
				});
				resumeMenuItem.addActionListener(new ActionListener() {
					@Override 
					public void actionPerformed(ActionEvent e) {
						gameController.resume();
					}
				});
			}
		}
		
	}
	
	public JMenuBar menuBar(){
		return this.menu;
	}
}