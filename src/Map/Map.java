package Map;

import java.io.Serializable;
import java.util.ArrayList;

import Background.Background;
import Block.Block;

/**
 * Record the array of backgrounds, array list of blocks and position of tanks. Can edit by editor and read by game application.
 * @author huangzhangyu
 */
public class Map implements Serializable{

	private static final long serialVersionUID = 1L;
	private Background[][] backgrounds;  //A 2D array which records which type of background should be put on position [y][x].
	private ArrayList<Block> blocks;  //An array list record blocks should be put on the map.
	private int xSize;  //Size in pixels on X coordinate.
	private int ySize;  //Size in pixels on Y coordinate.
	private int pictureSize = 50;  //Size of picture in pixels.

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
			if (block.getX() == x && block.getY() == y) {
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
			if (block.getX() == x && block.getY() == y) {
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
	
}
