package Block;

public interface Moveable {
	public enum Direction {
		W  (-2, 0, "static/picture/tankWest.png"      , "static/picture/bulletWest.png"),
		NW (-2,-2, "static/picture/tankNorthWest.png" , "static/picture/bulletNorthWest.png"), 
		N  ( 0,-2, "static/picture/tankNorth.png"     , "static/picture/bulletNorth.png"), 
		NE ( 2,-2, "static/picture/tankNorthEast.png" , "static/picture/bulletNorthEast.png"), 
		E  ( 2, 0, "static/picture/tankEast.png"      , "static/picture/bulletEast.png"), 
		SE ( 2, 2, "static/picture/tankSouthEast.png" , "static/picture/bulletSouthEast.png"), 
		S  ( 0, 2, "static/picture/tankSouth.png"     , "static/picture/bulletSouth.png"), 
		SW (-2, 2, "static/picture/tankSouthWest.png" , "static/picture/bulletSouthWest.png"), 
		STOP(0, 0);

		private int xSpeed;
		private int ySpeed;
		private String tankPic;
		private String bulletPic;
		Direction(int xSpeed, int ySpeed, String tankPic, String bulletPic) {
			this.xSpeed = xSpeed;
			this.ySpeed = ySpeed;
			this.tankPic = tankPic;
			this.bulletPic = bulletPic;
		}
		Direction(int xSpeed, int ySpeed) {
			this.xSpeed = xSpeed;
			this.ySpeed = ySpeed;
		}
		public int getxSpeed() {
			return xSpeed;
		}
		public int getySpeed() {
			return ySpeed;
		}
		public String getTankPic() {
			return tankPic;
		}
		public String getBulletPic() {
			return bulletPic;
		}
	}
}
