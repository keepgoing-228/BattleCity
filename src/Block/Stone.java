package Block;

import java.io.Serializable;

public class Stone extends Block implements Serializable {

	private static final long serialVersionUID = 1L;

	public Stone() {
		
		picturePath = "static/picture/stoneblock.png";
		this.setBlood(5);
		this.setBreakable(true);
		this.setSize(50);
		this.setPicture(picturePath);
		
	}
	
	/**
	 * @param x x coordinate of the stone
	 * @param y y coordinate of the stone
	 */
	public Stone(int x, int y) {
		
		this();
		this.setX(x);
		this.setY(y);
		
	}
	
	public String toString() {
		return "Stone";
	}
	
	public Stone clone() {
		
		Stone stone = new Stone();
		stone.setX(this.getX());
		stone.setY(this.getY());
		return stone;
		
	}
	
}
