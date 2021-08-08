package Game;

import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Block.Block;
import Map.Map;

/**
 * A section of the game. Every level should have its own map setting. These settings should define by using method buildMap() to read a serialized map.
 * @author huangzhangyu 
 */

public abstract class Level{
	
	protected Map map;
	protected JLayeredPane pane = new JLayeredPane();  //show every component
	protected JPanel backgroundPanel;  //layer to draw background
	protected JPanel blockPanel;  //layer to draw real object
	protected int xSize = 800;
	protected int ySize = 600;
	
	/**
	 * Read a map from static/map folder
	 */
	protected void buildMap(String name) {
		
		try {
			FileInputStream fileInputStream = new FileInputStream("static/map/" + name);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			map = (Map) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Draw background objects in the map onto background panel. This method should only be called once.
	 */
	protected void renderBackground() {
		
		this.backgroundPanel = new JPanel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < map.getxSize() / 50; i++) {
					for (int j = 0; j < map.getySize()/ 50; j++) {
						System.out.printf("%d %d\n", i, j);
						g.drawImage(map.getBackground(i, j).getPicture(), i * 50, j * 50, this);  //Draw the background on (i * 50, j * 50).
					}
				}
			}

		};

		this.backgroundPanel.setBounds(0, 0, xSize, ySize);

	}

	/**
	 * Draw blocks in the map onto block panel. This method should be called every frame.
	 */
	protected void renderBlock() {

		blockPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < map.getBlocksSize(); i++) {
					Block block = map.getBlock(i);
					g.drawImage(block.getPicture(), block.getX() * 50, block.getY() * 50, this);
				}
			}

		};
		blockPanel.setBounds(0, 0, xSize, ySize);
		blockPanel.setOpaque(false);
		
	}

	/**
	 * repaint the picture
	 */
	protected abstract void runAction();

}
