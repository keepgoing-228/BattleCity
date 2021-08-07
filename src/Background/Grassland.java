package Background;

import java.io.Serializable;

public class Grassland extends Background implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Grassland () {
		picturePath = "static/picture/grassland.png";
		this.setWalkable(true);
		this.setPicture(picturePath);
	}
	
	public String toString() {
		return "Grassland";
	}
	
	public Grassland clone() {
		return new Grassland();
	}
	
}
