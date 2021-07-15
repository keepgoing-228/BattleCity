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
	private JLabel picture; //Label for showing the picture
	private boolean walkable; //Whether tank can walk on this type of background
	private Image image;  //Picture of the background block

	/**
	 * @param path the direction of the picture file
	 * @return Image in 25x25 pixel
	 */
	public Image getTransformedImage(String path) {

		try {
			Image image = ImageIO.read(new File(path));
			image = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH); //Read the file
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public JLabel getPicture() {
		return picture;
	}
	public void setPicture(JLabel picture) {
		this.picture = picture;
	}
	public boolean isWalkable() {
		return walkable;
	}
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
