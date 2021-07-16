package Block;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Block {

	private int blood;
	private Image picture;
	private int size = 25;  //the block will be size x size big on the frame, used for damage and collision judgment
	private int[] poisition;  //(x, y) position on the frame
	private boolean breakable;  //Whether player or computer can damage this block
	
	/**
	 * @param path the direction of the picture file
	 * @return Image in size x size pixel
	 */
	public Image setPicture(String path) {

		try {
			Image image = ImageIO.read(new File(path));
			image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH); //Read the file
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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

	public int[] getPoisition() {
		return poisition;
	}

	public void setPoisition(int[] poisition) {
		this.poisition = poisition;
	}

	public boolean isBreakable() {
		return breakable;
	}

	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}

	public Image getPicture() {
		return picture;
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
	
	
}
