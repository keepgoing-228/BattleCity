package Game;

import java.awt.Graphics;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Background.Background;

/**
 * @author huangzhangyu
 * A section of the game. Every level should have its own map, block and enemies settings. These settings should define by overriding abstract method "drawMap".
 */
public abstract class Level{
	
	protected Background[][] map = new Background[24][32];  //the position of background
	protected JLayeredPane panel;  //show every component
	protected JPanel background;  //layer to draw background
	protected JPanel block;  //layer to draw real object
	
	
	/**
	 * Make the map of the level by putting background object into map array in order.
	 */
	protected abstract void drawMap();
	
	/**
	 * Draw background objects in the map onto background panel. This method should only be called once.
	 */
	protected void renderBackground() {
		
		this.background = new JPanel() {
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {
						g.drawImage(map[i][j].getImage(), j * 25, i * 25, this);
					}
				}
			}
			
		};
		
	}
	
	
}
