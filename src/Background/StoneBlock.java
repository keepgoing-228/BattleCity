package Background;

import javax.swing.JLabel;

public class StoneBlock extends Background {
	
	public StoneBlock() {
		this.setWalkable(false);
		this.setPicture(new JLabel(this.getTransformedIcon("static/picture/stoneblock.png")));
	}
	
	public String toString() {
		return "This is a stoneblock";
	}
}
