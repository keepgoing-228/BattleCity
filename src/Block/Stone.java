package Block;

public class Stone extends Block {

	/**
	 * @param x x coordinate of the stone
	 * @param y y coordinate of the stone
	 */
	public Stone(int x, int y) {
		
		this.setBlood(5);
		this.setBreakable(true);
		this.setX(x);
		this.setY(y);
		this.setSize(25);
		this.setPicture("static/picture/stoneblock.png");
		
	}
	
}
