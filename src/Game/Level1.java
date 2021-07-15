package Game;

import Background.Grassland;
import Background.River;

public class Level1 extends Level {
	
	public Level1() {
		
		this.drawMap();
		this.renderBackground();
		
	}

	@Override
	protected void drawMap() {
		
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
		
	}
	
}
