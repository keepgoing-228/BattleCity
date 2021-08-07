package Map;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Background.Background;
import Background.Grassland;
import Block.Block;
import Block.Stone;

public class test implements Serializable {

	public static void main(String[] args) {

		Grassland b = new Grassland();
		try {
			FileOutputStream f = new FileOutputStream("b");
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(b);
			o.flush();
			o.close();
			f.close();
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
