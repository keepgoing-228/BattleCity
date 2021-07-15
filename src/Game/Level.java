package Game;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import Background.Background;

public abstract class Level{
	
	protected Background[][] map;
	protected JFrame frame;
	
	public Level(){
		this.map = new Background[24][32];
		this.frame = new JFrame();
		
		this.frame.setSize(815, 640);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setLayout(new GridBagLayout());;
		
	}
	/**
	 * 依序把map和實體放入frame
	 */
	public abstract void render();
}
