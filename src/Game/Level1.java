package Game;

import java.awt.Dimension;

import javax.swing.JLayeredPane;

import Background.Grassland;
import Background.River;
import Block.Stone;

public class Level1 extends Level {
	
	public Level1() {
		
		this.buildMap();
		this.buildBlocks();
		this.renderBackground();
		this.renderBlock();
		this.pane.setPreferredSize(new Dimension(800, 629));
		this.background.setBounds(0, 0, 800, 629);
		this.pane.add(background, JLayeredPane.DEFAULT_LAYER);
		this.block.setOpaque(false);
		this.block.setBounds(0, 0, 800, 629);
		this.pane.add(block,JLayeredPane.PALETTE_LAYER);
		
	}

	@Override
	protected void buildMap() {
		
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

	@Override
	protected void buildBlocks() {
		
		int y = 20;
		int x1 = 12;
		int x2 = 18;
		
		for (int i = 12; i < 19; i++) {
			this.blocks.add(new Stone(i, y));
			System.out.printf("Build block at %d, %d\n", i, y);
		}
		
		for (int i = 20; i < 24; i++) {
			this.blocks.add(new Stone(x1, i));
			this.blocks.add(new Stone(x2, i));
			System.out.printf("Build block at %d, %d\n", x1, i);
			System.out.printf("Build block at %d, %d\n", x2, i);
		}
		
	}
	
}
