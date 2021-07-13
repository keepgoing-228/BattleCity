package Game;

import java.awt.GridBagConstraints;

import Background.Grassland;
import Background.River;

public class Level1 extends Level {

	public Level1() {

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < map[1].length; j++) {
				map[i][j] = new Grassland();
			}
		}
		
		for (int i = 11; i < 13; i++) {
			for (int j = 0; j < map[1].length; j++) {
				map[i][j] = new River();
			}
		}
		
		for (int i = 13; i < map.length; i++) {
			for (int j = 0; j < map[1].length; j++) {
				map[i][j] = new Grassland();
			}
		}

		render();
	}
	
	public void render() {
		GridBagConstraints c;
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				c.gridx = j;
				c.gridy = i;
				this.frame.add(map[i][j].getPicture(), c);
				System.out.printf("%d, %d \n", i, j);
				System.out.println(map[i][j]);
			}
		}
	}
	
}
