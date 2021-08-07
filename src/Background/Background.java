package Background;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import Map.Material;

/**
 * @author huangzhangyu
 * Class for objects to draw on the background of the game. Object on the background cannot be touched
 */
public abstract class Background implements Material, Serializable{
	
	private static final long serialVersionUID = 1L;
	private boolean walkable; //Whether tank can walk on this type of background
	private transient Image picture = null;  //Picture of the background block
	private int size = 50;
	protected String picturePath = null;
	
	/**
	 * @param path the direction of the picture file
	 * @return Image in 50x50 pixel
	 */
	public void setPicture(String path) {

		try {
			Image image = ImageIO.read(new File(path));
			this.picture = image.getScaledInstance(size, size, Image.SCALE_SMOOTH); //Read the file
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean isWalkable() {
		return walkable;
	}
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	public Image getPicture() {
		
		if (picture == null) {
			setPicture(picturePath);
		}
		return picture;
	}
	
	public abstract Background clone();
		
}
