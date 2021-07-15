package Game;

import java.awt.GridBagConstraints;

import Background.Background;
import Background.Grassland;
import Background.River;
import Background.StoneBlock;

public class Level2 extends Level {
	
	private int gdy;
	private int gdx;
	
	public Level2() {
		//set all land are grassland  24x32
		setAllGrassland();
		
		
		//river and stone
		setRiver(11,13,0,32);
		
		setStoneBlock(0,5,11,13);
		setStoneBlock(5,7,11,21);
		setStoneBlock(0,5,19,21);
		
		setStoneBlock(16,18,0,7);
		setStoneBlock(18,24,5,7);
		setStoneBlock(16,18,25,32);
		setStoneBlock(18,24,25,27);
		
		
		//use gridbag to put all map
		render();

	}
	

	
	public void render() {
		GridBagConstraints c;
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		for (gdy = 0; gdy < map.length; gdy++) {
			for (gdx = 0; gdx < map[0].length; gdx++) {
				c.gridx = gdx;
				c.gridy = gdy;
				this.frame.add(map[gdy][gdx].getPicture(), c);
//				System.out.printf("%d, %d \n", gdy, gdx);
//				System.out.println(map[gdy][gdx]);
			}
		}
	}
	
	public void setAllGrassland() {
		for(gdy = 0; gdy < map.length ; gdy++) {
			for(int gdx = 0; gdx < map[0].length; gdx++) {
				map[gdy][gdx] = new Grassland();
			}
		}
	}
	
	public void setRiver(int y1, int y2, int x1, int x2) {
		for (gdy = y1; gdy < y2; gdy++) {
			for (gdx = x1; gdx < x2; gdx++) {
				map[gdy][gdx] = new River();
			}
		}
	}
	
	public void setStoneBlock(int y1, int y2, int x1, int x2) {
		for (gdy = y1; gdy < y2; gdy++) {
			for (gdx = x1; gdx < x2; gdx++) {
				map[gdy][gdx] = new StoneBlock();
			}
		}
	}


}
