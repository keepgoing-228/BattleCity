package Background;

public class River extends Background {
	
	public River() {
		this.setWalkable(false);
		this.setImage((this.getTransformedImage("static/picture/river.png")));
	}
	
	public String toString() {
		return "This is a river";
	}
}
