package Map;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

import Background.Background;
import Block.Block;
import Block.Bullet;
import Block.Moveable.Direction;
import Block.Tank;

/**
 * Record the array of backgrounds, array list of blocks and position of tanks. Can edit by editor and read by game application.
 * @author huangzhangyu
 */
public class Map implements Serializable{

	private static final long serialVersionUID = 1L;
	private Background[][] backgrounds;  //A 2D array which records which type of background should be put on position [y][x].
	private ArrayList<Block> blocks;  //An array list record blocks should be put on the map.
//	private ArrayList<Bullet> bullets; //An array list record every bullets should be draw on the map.
	private int xSize;  //Size in pixels on X coordinate.
	private int ySize;  //Size in pixels on Y coordinate.
	private int pictureSize = 50;  //Size of picture in pixels.
	private Tank tank = null;  //Player's tank.
	private Bullet bullet = null;
	
	/**
	 * Generate a map in size xSize * ySize pixels
	 * @param xSize How many pixels on x coordinate
	 * @param ySize How many pixels on y coordinate
	 */
	public Map(int xSize, int ySize, int pictureSize) {
		backgrounds = new Background[ySize / pictureSize][xSize / pictureSize];  //Each coordinate has Size / pictureSize blocks.
		blocks = new ArrayList<Block>();
		this.xSize = xSize;
		this.ySize = ySize;
		this.pictureSize = pictureSize;
	}
	
	/**
	 * Set a piece of background into backgrounds array in coordinate (x, y)
	 * @param background Type of background to be set
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void setBackground(Background background, int x, int y) {
		
		this.backgrounds[y][x] = background;
		
	}
	
	public Background getBackground(int x, int y) {
		
		return backgrounds[y][x];
		
	}
	
	public void addBlock(Block block) {
		
		blocks.add(block);
		
	}
	
	public int getBlocksSize() {
		return blocks.size();
	}

	public Block getBlock(int i) {
		return blocks.get(i);
	}
	
	/**
	 * Get a block in block array list which is on coordinate (x, y), if there is no such block on the position, return null.
	 * @param x x coordinate. 
	 * @param y y coordinate.
	 * @return A block or null.
	 */
	public Block getBlock(int x, int y) {
		
		for (int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			if (x >= block.getX() && x <= block.getX() + pictureSize && y >= block.getY() && y <= block.getY() + pictureSize) {
				return block;
			}
		}
		
		return null;
		
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}
	
	/**
	 * Check if there is a block on (x, y).
	 * @param x x coordinate.
	 * @param y y coordinate.
	 * @return True if there is a block, false if there isn't.
	 */
	public boolean hasBlock(int x, int y) {
		
		for (int i = 0; i < blocks.size(); i++) {
			
			Block block = blocks.get(i);
			if (x >= block.getX() && x <= block.getX() + pictureSize && y >= block.getY() && y <= block.getY() + pictureSize) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * Remove block on (x, y), if there doesn't have a block, nothing will happen.
	 * @param x x coordinate.
	 * @param y y coordinate.
	 */
	public void removeBlock(int x, int y) {
		
		for (int i = 0; i < blocks.size(); i++) {
			
			Block block = blocks.get(i);
			if (block.getX() == x && block.getY() == y) {
				blocks.remove(block);
			}
		}
		
	}
	
	public int getPictureSize() {
		return pictureSize;
	}
	
	public void setTank(Tank tank) {
		this.tank = tank;
	}
	
	public Tank getTank() {
		return this.tank;
	}
	
	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	
	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // store the keyboard in integer
		switch (key) {
		case KeyEvent.VK_CONTROL:
			//生成bullet
			bullet = new Bullet(tank.dir, tank.getX(), tank.getY());
			bulletMove();
			break;
		}
	}

	public void bulletMove() {
		if(bullet.isLive()) {
			bullet.setX(tank.getX());
			bullet.setY(tank.getY());
			
			
			String tempDir = null;
			//如何獲取tank pic轉成bullet pic
			switch(tank.dir.getTankPic()) {
			case "static/picture/tankWest.png":
				tempDir = "static/picture/bulletWest.png";
				break;
			case "static/picture/tankNorthWest.png":
				tempDir = "static/picture/bulletNorthWest.png";
				break;
			case "static/picture/tankNorth.png":
				tempDir = "static/picture/bulletNorth.png";
				break;
			case "static/picture/tankNorthEast.png":
				tempDir = "static/picture/bulletNorthEast.png";
				break;
			case "static/picture/tankEast.png":
				tempDir = "static/picture/bulletEast.png";
				break;
			case "static/picture/tankSouthEast.png":
				tempDir = "static/picture/bulletSouthEast.png";
				break;
			case "static/picture/tankSouth.png":
				tempDir = "static/picture/bulletSouth.png";
				break;
			case "static/picture/tankSouthWest.png":
				tempDir = "static/picture/bulletSouthWest.png";
				break;
			
			}
			bullet.setPicture(tempDir);
			
			int newX = bullet.getX() + bullet.dir.getxSpeed();
			int newY = bullet.getY() + bullet.dir.getySpeed();
			System.out.println(newX);
			System.out.println(newY);
			
			if (checkBlockCollision(newX, bullet.getY(), 48) == null && isWalkable(newX, bullet.getY(), 48)) {
				bullet.setX(newX);
			}
			
			if (checkBlockCollision(bullet.getX(), newY, 48) == null && isWalkable(bullet.getX(), newY, 48)) {
				bullet.setY(newY);
			}
			
		}
	}
	
	
	/**
	 * Move player's tank and change picture depend on key press. Nothing will happen if no key is pressed or the tank is trying to walk into an unwalkable place.
	 */
	public void tankMove() {
		if(!(!tank.bL&&!tank.bU&&!tank.bR&&!tank.bD)) {
			tank.setPicture(tank.dir.getTankPic());
		}
		int newX = tank.getX() + tank.dir.getxSpeed();
		int newY = tank.getY() + tank.dir.getySpeed();
		
		if (checkBlockCollision(newX, tank.getY(), 48) == null && isWalkable(newX, tank.getY(), 48)) {
			tank.setX(newX);
		}
		
		if (checkBlockCollision(tank.getX(), newY, 48) == null && isWalkable(tank.getX(), newY, 48)) {
			tank.setY(newY);
		}
		
	}
	
	private Block checkBlockCollision(int x, int y, int pictureSize) {
		
		if (hasBlock(x, y)) {
			return getBlock(x, y);
		} else if (hasBlock(x, y + pictureSize)) {
			return getBlock(x, y + pictureSize);
		} else if (hasBlock(x + pictureSize, y)) {
			return getBlock(x + pictureSize, y);
		} else {
			return getBlock(x + pictureSize, y + pictureSize);
		}

		
	}
	
	private boolean checkBundaryCollision(int x , int y, int pictureSize) {
		
		if ( x * y <= 0 || x + pictureSize > xSize || y + pictureSize > ySize) {
			return true;
		} 
		return false;
	}
	
	private boolean isWalkable(int x, int y, int pictureSize) {
		
//		System.out.printf("x1: %d, y1: %d x2:%d, y2:%d\n", x, y, x + pictureSize, y + pictureSize);
		
		if ( x * y <= 0 || x + this.pictureSize >= xSize || y + this.pictureSize >= ySize) {
			return false;
		} 
		
		Background northWestBackground = backgrounds[y / this.pictureSize][x / this.pictureSize];
		Background northEastBackground = backgrounds[y / this.pictureSize][(x + pictureSize) / this.pictureSize];
		Background southEastBackground = backgrounds[(y + pictureSize) / this.pictureSize][(x + pictureSize) / this.pictureSize];
		Background southWestBackground = backgrounds[(y + pictureSize) / this.pictureSize][x / this.pictureSize];
		
		return northWestBackground.isWalkable() && northEastBackground.isWalkable() && southEastBackground.isWalkable() && southWestBackground.isWalkable();
		
	}
	
}
