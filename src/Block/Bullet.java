package Block;

import java.awt.event.KeyEvent;



public class Bullet extends Block implements Moveable{

	private static final long serialVersionUID = 1L;
	public Direction dir;
	private boolean live = true;
	private int bounceCounter = 0;
	
	
	public Bullet(Direction dir, int x, int y) {
		this.dir = dir;
		this.setX(x);
		this.setY(y);
		this.setBreakable(false);

	}
		
	
	
	@Override
	public Block clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	public int getBounceCounter() {
		return bounceCounter;
	}
	public void setBounceCounter(int bounceCounter) {
		this.bounceCounter = bounceCounter;
	}
	
}
