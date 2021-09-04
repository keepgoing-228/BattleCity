package Block;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Map.Material;

public abstract class Block implements Material {

	private static final long serialVersionUID = 1L;
	private int blood;
	private transient Image picture = null;
	private int size = 50;  //the block will be size x pixel big on the frame, used for damage and collision judgment.
	private int x = 0;  //x coordinate on the frame in pixels.
	private int y = 0;  //y coordinate on the frame in pixels.
	private boolean breakable;  //Whether player or computer can damage this block.
	protected String picturePath = null;
	
	
	
	/**
	 * @param path the direction of the picture file
	 * @return Image in size x size pixel
	 */
	public void setPicture(String path) {

		try {
			Image image = ImageIO.read(new File(path));
			this.picture = image.getScaledInstance(size, size, Image.SCALE_SMOOTH); //Read the file
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isBreakable() {
		return breakable;
	}

	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}

	public Image getPicture() {
		
		if (picture == null) {
			this.setPicture(picturePath);
		}
		return picture;
		
	}
	
	public void setPicture(Image picture) {
		this.picture = picture;
	}
	
	/**
	 * Minus the blood of the block after collision with a bullet
	 * @param damage
	 * @return
	 */
	public int damage(int damage) {
		
		if (this.blood - damage > 0) {
			this.blood -= damage;
			return this.blood;
		} else {
			this.blood = 0;
			return 0;
		}
		
	}
	
	public abstract Block clone();
	
}
