package Background;

public class Grassland extends Background {
	
	public Grassland () {
		this.setWalkable(true);
		this.setImage((this.getTransformedImage("static/picture/grassland.png")));
	}
	
	public String toString() {
		return "This is a grassland";
	}
	
}
