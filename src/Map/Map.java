package Map;

import java.io.Serializable;
import java.util.ArrayList;

import Background.Background;
import Block.Block;

public class Map implements Serializable{

	private static final long serialVersionUID = 1L;
	private Background[][] backgrounds;
	private ArrayList<Block> blocks;
	private int xSize;
	private int ySize;
	private int pictureSize;

	/**
	 * Generate a map in size xSize * ySize blocks 
	 * @param xSize How many blocks in x coordinate
	 * @param ySize How many blocks in y coordinate
	 */
	public Map(int xSize, int ySize, int pictureSize) {
		backgrounds = new Background[ySize / pictureSize][xSize / pictureSize];
		blocks = new ArrayList<Block>();
		this.xSize = xSize;
		this.ySize = ySize;
		this.pictureSize = pictureSize;
	}
	
	/**
	 * Set background into backgrounds in coordinate (x, y)
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
	
	public boolean hasBlock(int x, int y) {
		
		for (int i = 0; i < blocks.size(); i++) {
			
			Block block = blocks.get(i);
			if (block.getX() == x && block.getY() == y) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
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
