package Background;

import javax.swing.JLabel;

public class River extends Background {
	
	public River() {
		this.setWalkable(false);
		this.setPicture(new JLabel(this.getTransformedIcon("static/picture/river.png")));
	}
	
	public String toString() {
		return "This is a river";
	}
}
