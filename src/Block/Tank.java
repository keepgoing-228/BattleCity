package Block;

import java.awt.event.KeyEvent;

public class Tank extends Block implements Moveable{
	
	private static final long serialVersionUID = 1L;
	public boolean bL=false, bU=false, bR=false, bD=false;  // control of the dir
	public Direction dir = Direction.STOP;

	public Tank() {
		
		this.setBlood(10);
		this.setBreakable(true);
		picturePath = "static/picture/tankNorth.png";
		this.setPicture(picturePath); //initial direction is north
		
	}
	

	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // store the keyboard in integer
		switch (key) {
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_CONTROL:
			//¥Í¦¨bullet
			
			break;
		}
		locateDirection(); //composite
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode(); // store the keyboard in integer
		switch (key) {
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_W:
			bU = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		case KeyEvent.VK_S:
			bD = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_D:
			bR = false;
			break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_A:
			bL = false;
			break;
		}
		locateDirection(); //composite
	}
	public void locateDirection(){
		if(bL&&!bU&&!bR&&!bD) dir=Direction.W;
		else if(bL&&bU&&!bR&&!bD) dir=Direction.NW;
		else if(!bL&&bU&&!bR&&!bD) dir=Direction.N;
		else if(!bL&&bU&&bR&&!bD) dir=Direction.NE;
		else if(!bL&&!bU&&bR&&!bD) dir=Direction.E;
		else if(!bL&&!bU&&bR&&bD) dir=Direction.SE;
		else if(!bL&&!bU&&!bR&&bD) dir=Direction.S;
		else if(bL&&!bU&&!bR&&bD) dir=Direction.SW;
		else if(!bL&&!bU&&!bR&&!bD) dir=Direction.STOP;
	}


	@Override
	public Block clone() {
		
		Tank tank = new Tank();
		tank.setX(this.getX());
		tank.setY(this.getY());
		return tank;
	
	}
	
	@Override
	public String toString() {
		
		return "Tank";
		
	}


}
