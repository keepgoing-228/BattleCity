package Game;

import java.awt.Graphics;
<<<<<<< HEAD
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ArrayList;
=======
import java.io.FileInputStream;
import java.io.ObjectInputStream;
>>>>>>> upstream/master

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Block.Block;
import Map.Map;

/**
 * @author huangzhangyu A section of the game. Every level should have its own
 *         map, block and enemies settings. These settings should define by
 *         overriding abstract method "drawMap".
 */
<<<<<<< HEAD
public abstract class Level {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	protected Background[][] map = new Background[24][32]; // the position of background
	protected ArrayList<Block> blocks = new ArrayList<Block>(); // blocks on the map
	protected JLayeredPane pane = new JLayeredPane(); // show every component
	protected JPanel backgroundPanel; // layer to draw background
	protected JPanel blockPanel; // layer to draw real object

	/**
	 * Build the map of the level by putting background object into map array in
	 * order.
	 */
	protected abstract void buildMap();

=======
public abstract class Level{
	
	protected Map map;
	protected JLayeredPane pane = new JLayeredPane();  //show every component
	protected JPanel backgroundPanel;  //layer to draw background
	protected JPanel blockPanel;  //layer to draw real object
	protected int xSize = 800;
	protected int ySize = 600;
	
>>>>>>> upstream/master
	/**
	 * Read a map from static/map folder
	 */
<<<<<<< HEAD
	protected abstract void buildBlocks();

=======
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
	
>>>>>>> upstream/master
	/**
	 * Draw background objects in the map onto background panel. This method should
	 * only be called once.
	 */
	protected void renderBackground() {
<<<<<<< HEAD

		this.backgroundPanel = new JPanel() {

=======
		
		this.backgroundPanel = new JPanel() {
			
>>>>>>> upstream/master
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < map.getxSize() / 50; i++) {
					for (int j = 0; j < map.getySize()/ 50; j++) {
						System.out.printf("%d %d\n", i, j);
						g.drawImage(map.getBackground(i, j).getPicture(), i * 50, j * 50, this);
					}
				}
			}

		};

		this.backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);

	}

	protected void renderBlock() {
<<<<<<< HEAD

		this.blockPanel = new JPanel() {
=======
		
		blockPanel = new JPanel() {
>>>>>>> upstream/master

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < map.getBlocksSize(); i++) {
					Block block = map.getBlock(i);
					g.drawImage(block.getPicture(), block.getX() * 50, block.getY() * 50, this);
				}
			}

		};
<<<<<<< HEAD

		this.blockPanel.setBounds(0, 0, WIDTH, HEIGHT);

=======
		blockPanel.setBounds(0, 0, xSize, ySize);
		blockPanel.setOpaque(false);
		
>>>>>>> upstream/master
	}

	/**
	 * repaint the picture
	 */
	protected abstract void runAction();

}
