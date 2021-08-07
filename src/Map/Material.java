package Map;

import java.awt.Image;
import java.io.Serializable;

/**
 * Material which can be put on a map.
 * @author huangzhangyu
 */
public interface Material extends Cloneable, Serializable {

	public abstract Material clone();
	public abstract Image getPicture();
	
}
