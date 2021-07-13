package Background;

import javax.swing.JLabel;

public class Grassland extends Background {

	public Grassland() {
		this.setWalkable(true);
		this.setPicture(new JLabel(this.getTransformedIcon("static/picture/grassland.png")));
	}
	
	public String toString() {
		return "This is a piece of grassland";
	}
	
}
