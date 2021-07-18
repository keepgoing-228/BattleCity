package Game;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Background.Background;
import Block.Block;

/**
 * @author huangzhangyu
 * A section of the game. Every level should have its own map, block and enemies settings. These settings should define by overriding abstract method "drawMap".
 */
public abstract class Level{
	
	protected Background[][] map = new Background[24][32];  //the position of background
	protected ArrayList<Block> blocks = new ArrayList<Block>();  //blocks on the map
	protected JLayeredPane pane = new JLayeredPane();  //show every component
	protected JPanel background;  //layer to draw background
	protected JPanel block;  //layer to draw real object
	
	
	/**
	 * Build the map of the level by putting background object into map array in order.
	 */
	protected abstract void buildMap();
	
	/**
	 * Generate and put blocks into array list.
	 */
	protected abstract void buildBlocks();
	
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
						g.drawImage(map[i][j].getPicture(), j * 25, i * 25, this);
					}
				}
			}
			
		};
		
	}
	
	protected void renderBlock() {
		
		this.block = new JPanel() {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < blocks.size(); i++) {
					Block block = blocks.get(i);
					g.drawImage(block.getPicture(), block.getX() * 25, block.getY() * 25, this);
				}
			}
			
		};
		
	}
	
	
}
