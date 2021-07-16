package Background;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * @author huangzhangyu
 * Class for objects to draw on the background of the game. Object on the background cannot be touched
 */
public abstract class Background {
	private boolean walkable; //Whether tank can walk on this type of background
	private Image picture;  //Picture of the background block

	/**
	 * @param path the direction of the picture file
	 * @return Image in 25x25 pixel
	 */
	public void setPicture(String path) {

		try {
			Image image = ImageIO.read(new File(path));
			this.picture = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH); //Read the file
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
		return picture;
	}
	
}
