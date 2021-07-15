package Game;

import javax.swing.JFrame;

public class App {

	public static void main(String[] args) {
		
		//Create frame of the game
		JFrame frame = new JFrame();
		frame.setSize(800, 629);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Choose the level player want to play
		Level level = new Level1();
		frame.add(level.background);
		frame.setVisible(true);
		
	}

}
