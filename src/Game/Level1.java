package Game;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import javax.swing.JLayeredPane;

import Background.Grassland;
import Background.River;
import Block.Stone;

public class Level1 extends Level {
	
	public Level1() {
		
		this.buildMap("test");
		this.renderBackground();
		this.renderBlock();
		this.pane.setPreferredSize(new Dimension(800, 629));
		this.backgroundPanel.setBounds(0, 0, 800, 629);
		this.pane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
		this.blockPanel.setOpaque(false);
		this.blockPanel.setBounds(0, 0, 800, 629);
		this.pane.add(blockPanel,JLayeredPane.PALETTE_LAYER);
		
	}
	
}
