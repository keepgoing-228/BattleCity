package Background;

import java.io.Serializable;

public class River extends Background implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public River() {
		picturePath = "static/picture/river.png";
		this.setWalkable(false);
		this.setPicture(picturePath);
	}
	
	public String toString() {
		return "River";
	}
	
	public River clone() {
		return new River();
	}
}
