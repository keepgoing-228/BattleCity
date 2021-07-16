package Background;

public class River extends Background {
	
	public River() {
		this.setWalkable(false);
		this.setPicture("static/picture/river.png");
	}
	
	public String toString() {
		return "This is a river";
	}
}
