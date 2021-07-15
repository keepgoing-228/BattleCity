package Background;

public class StoneBlock extends Background {
	
	public StoneBlock() {
		this.setWalkable(false);
		this.setImage(this.getTransformedImage("static/picture/stoneblock.png"));
	}
	
	public String toString() {
		return "This is a stoneblock";
	}
}
