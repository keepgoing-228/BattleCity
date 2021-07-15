package Background;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author huangzhangyu、AUAS
 * 背景類，所有道路的根源，紀錄道路的貼圖、行走資訊
 */
public abstract class Background {
	private JLabel picture; //貼圖在Swing中是JLabel
	private boolean walkable; //是否可以行走
	
	
	/**
	 * @param path 圖片位置
	 * @return 大小相同的圖片
	 */
	public ImageIcon getTransformedIcon(String path) {
		ImageIcon icon = new ImageIcon(path);
		Image image = icon.getImage();
		image = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH); //修飾每個相片元素
		return new ImageIcon(image);
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
}
