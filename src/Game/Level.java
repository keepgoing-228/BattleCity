package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Block.Block;
import Block.Bullet;
import Block.Tank;
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
	public KeyAdapter keyAdapter = new KeyMoniton();
	
	/**
	 * Read a map from static/map folder
	 */
	protected void readMap(String name) {
		
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
					g.drawImage(block.getPicture(), block.getX(), block.getY(), this);
				}
				Tank myTank = map.getTank();
				if (myTank != null) {
					g.drawImage(myTank.getPicture(), myTank.getX(), myTank.getY(), this);				
				}
				Bullet myBullet = map.getBullet();
				if (myBullet != null) {
					g.drawImage(myBullet.getPicture(), myBullet.getX(), myBullet.getY(), this);
				}
			}

		};
		blockPanel.setBounds(0, 0, xSize, ySize);
		blockPanel.setOpaque(false);
		
	}

	/**
	 * moving of function
	 */
	protected void runAction() {

		Timer timer = new Timer(30, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				map.tankMove();
//				map.bulletMove();

			

				pane.removeAll();
				pane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
				renderBlock();
				blockPanel.setOpaque(false);
				blockPanel.setBounds(0, 0, 800, 629);
				pane.add(blockPanel,JLayeredPane.PALETTE_LAYER);
				pane.repaint();
				pane.revalidate();	
			}

		});
		timer.start();

	}
	
	private class KeyMoniton extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyPressed(e);
			map.getTank().KeyPressed(e);
			map.KeyPressed(e);
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			super.keyReleased(e);
			map.getTank().keyReleased(e);
		}

	}
}
