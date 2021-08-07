package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Background.Grassland;
import Background.River;
import Block.Block;
import Block.Stone;
import Block.Tank;

public class Level2 extends Level {

	private int gdx, gdy;
	private JFrame frame;
	private Tank myTank = new Tank(180, 200);
	

	public Level2(JFrame frame) {
		
		this.frame = frame;
		this.buildMap();
		this.renderBackground();
		this.buildBlocks();
		this.renderBlock();
		this.pane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pane.add(backgroundPanel, 1);
		this.blockPanel.setOpaque(false); // the function of same panel
		this.pane.add(blockPanel, 0);

		this.frame.addKeyListener(new KeyMoniton());
		this.runAction();

	}
	private class KeyMoniton extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyPressed(e);
			myTank.KeyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyReleased(e);
			myTank.keyReleased(e);
		}
	}

	@Override
	protected void buildMap() {

		// set all land are grassland 24x32
		setAllGrassland();

		// river
		setRiver(11, 13, 0, 32);
	}

	@Override
	protected void buildBlocks() {

		// stone
		setStoneBlock(0, 5, 11, 13);
		setStoneBlock(5, 7, 11, 21);
		setStoneBlock(0, 5, 19, 21);

		setStoneBlock(16, 18, 0, 7);
		setStoneBlock(18, 24, 5, 7);
		setStoneBlock(16, 18, 25, 32);
		setStoneBlock(18, 24, 25, 27);

		// tank
//		this.blocks.add(myTank);

	}

	@Override
	protected void renderBlock() {

		this.blockPanel = new JPanel() {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < blocks.size(); i++) {
					Block block = blocks.get(i);
					g.drawImage(block.getPicture(), block.getX() * 25, block.getY() * 25, this);
				}
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();

				int xPos = myTank.getX();
				int yPos = myTank.getY();

				while ((yPos < getHeight()) && (xPos < getWidth())) { // keep painting
					g2d.drawImage(myTank.getPicture(), xPos, yPos, this);
					xPos += getWidth();
					yPos += getHeight();
				}

				g2d.dispose();
			}

		};

		this.blockPanel.setBounds(0, 0, WIDTH, HEIGHT);

	}

	/**
	 * tank moving of function
	 */
	protected void runAction() {
//		Timer test = new Timer()
		Timer timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(!myTank.bL&&!myTank.bU&&!myTank.bR&&!myTank.bD)) {
					myTank.setPicture(myTank.dir.getPic());
				}
				myTank.setY(myTank.getY() + myTank.dir.getySpeed());
				if (myTank.getY() > HEIGHT - 25) {
					myTank.setY(HEIGHT - 25);
				} else if ((myTank.getY() < 0)) {
					myTank.setY(0);
				}
				myTank.setX(myTank.getX() + myTank.dir.getxSpeed());
				if (myTank.getX() > WIDTH - 25) {
					myTank.setX(WIDTH - 25);
				} else if ((myTank.getX() < 0)) {
					myTank.setX(0);
				}

				Level2.this.blockPanel.repaint();
			}

		});
		timer.start();

	}

	public void setAllGrassland() {
		for (gdy = 0; gdy < map.length; gdy++) {
			for (int gdx = 0; gdx < map[0].length; gdx++) {
				map[gdy][gdx] = new Grassland();
			}
		}
	}

	public void setRiver(int y1, int y2, int x1, int x2) {
		for (gdy = y1; gdy < y2; gdy++) {
			for (gdx = x1; gdx < x2; gdx++) {
				map[gdy][gdx] = new River();
			}
		}
	}

	public void setStoneBlock(int y1, int y2, int x1, int x2) {
		for (gdy = y1; gdy < y2; gdy++) {
			for (gdx = x1; gdx < x2; gdx++) {
				this.blocks.add(new Stone(gdx, gdy));
//				System.out.printf("Build block at %d, %d\n", gdx, gdy);
			}
		}
	}

	

}
