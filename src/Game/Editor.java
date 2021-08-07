package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Background.Background;
import Background.Grassland;
import Background.River;
import Block.Block;
import Block.Stone;
import Map.Map;
import Map.Material;

public class Editor extends JFrame implements ActionListener{

	public static void main(String[] args) {
		
		Editor editor = new Editor(800, 600);
		editor.setVisible(true);
		
	}
	
	private static final long serialVersionUID = 1L;
	private JPanel buttonLayer = new JPanel();
	private JLayeredPane pane = new JLayeredPane();
	private Map map;
	private JPanel backgroundPanel;
	private JPanel blockPanel;
	private int xSize, ySize;
	private MaterialSelector materialSelector;
	private Material selectedMaterial;
	private int pictureSize = 50;

	public Editor(int xSize, int ySize) {
				
		super("Map Editor");
		this.xSize = xSize;
		this.ySize = ySize;
		this.setSize(xSize, ySize + 29);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		buttonLayer.setLayout(new GridBagLayout());
		JButton button;
		for (int i = 0; i < xSize / pictureSize; i++) {
			for (int j = 0; j < (ySize) / pictureSize; j++) {
				button = new JButton();
				button.setPreferredSize(new Dimension(pictureSize, pictureSize));
				c.gridx = i;
				c.gridy = j;
				button.setOpaque(false);
				button.setContentAreaFilled(false);
				button.setBorderPainted(false);
				button.setActionCommand(String.format("%d-%d", i, j));
				button.addActionListener(this);
				buttonLayer.add(button, c);
			}
		}
		buttonLayer.setBounds(0, 0, xSize, ySize);
		pane.setPreferredSize(new Dimension(xSize, ySize));
		pane.add(buttonLayer, JLayeredPane.DEFAULT_LAYER);
		
		map = new Map(xSize, ySize, 50);
		renderBackground();
		pane.add(backgroundPanel, JLayeredPane.PALETTE_LAYER);
				
		renderBlock();
		pane.add(blockPanel, JLayeredPane.MODAL_LAYER);
		
		this.setLayeredPane(pane);
				
		materialSelector = new MaterialSelector();
		materialSelector.setVisible(true);
				
	}

	private void renderBackground() {
		
		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 0, xSize, ySize);
		backgroundPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel picture;
		for (int i = 0; i < xSize / pictureSize; i++) {
			for (int j = 0; j < ySize / pictureSize; j++) {
				if (map.getBackground(i, j) != null) {
					picture = new JLabel(new ImageIcon(map.getBackground(i, j).getPicture()));
				} else {
					picture = new JLabel();
				}
				picture.setOpaque(false);
				picture.setPreferredSize(new Dimension(pictureSize, pictureSize));
				picture.setBorder(BorderFactory.createLineBorder(Color.black));
				c.gridx = i;
				c.gridy = j;
				backgroundPanel.add(picture, c);
			}
		}
		
	}
	
	private void renderBlock() {
		
		blockPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < map.getBlocksSize(); i++) {
					Block block = map.getBlock(i);
					g.drawImage(block.getPicture(), block.getX() * 50, block.getY() * 50, this);
				}
			}
			
		};
		blockPanel.setBounds(0, 0, xSize, ySize);
		blockPanel.setOpaque(false);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		int x = Integer.parseInt(e.getActionCommand().split("-")[0]);
		int y = Integer.parseInt(e.getActionCommand().split("-")[1]);
		
		pane.removeAll();
		if (selectedMaterial != null) {
			if (selectedMaterial.getClass().getSuperclass().getName().equals("Background.Background")) {
				map.setBackground((Background) selectedMaterial.clone(), x, y);
				renderBackground();
			} else if (selectedMaterial.getClass().getSuperclass().getName().equals("Block.Block")){
				Block block = (Block) selectedMaterial.clone();
				block.setX(x);
				block.setY(y);
				map.addBlock(block);
				renderBlock();
			} else {
				map.addBlock((Block) selectedMaterial.clone());
				renderBlock();
			}
		} else {
			if (map.hasBlock(x, y)) {
				map.removeBlock(x, y);
				renderBlock();
			} else {
				map.setBackground(null, x, y);
				renderBackground();
			}
		}

		
		pane.add(buttonLayer, JLayeredPane.DEFAULT_LAYER);
		pane.add(backgroundPanel, JLayeredPane.PALETTE_LAYER);
		pane.add(blockPanel, JLayeredPane.MODAL_LAYER);
		pane.repaint();
		pane.revalidate();
		this.revalidate();
		System.out.println(blockPanel.getSize());
		
	}
	
	private class MaterialSelector extends JFrame{
		
		private static final long serialVersionUID = 1L;
		private ArrayList<Material> materialList = new ArrayList<Material>();
		private JPanel selectorPanel;
		private ButtonGroup buttonGroup;
		private JTextField nameField = new JTextField(10);  //The name of this map.
		
		public MaterialSelector() {
			
			super("Item Selector");
			this.setSize(200, 400);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setLayout(new GridBagLayout());
			
			JMenu file = new JMenu("File");
			JMenuItem save = new JMenuItem("save");
			save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						FileOutputStream fileOutputStream = new FileOutputStream("static/map/" + nameField.getText());
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
						objectOutputStream.writeObject(map);
						objectOutputStream.flush();
						objectOutputStream.close();
						fileOutputStream.close();
						
					} catch(Exception e1) {
						e1.printStackTrace();
					}
					
				}
				
			});
			file.add(save);
			JMenuBar menuBar = new JMenuBar();
			menuBar.add(file);
			this.setJMenuBar(menuBar);
			
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(5, 5, 5, 5);
			c.fill = GridBagConstraints.BOTH;
			
			addBackgroundType();
			renderMaterial(0);
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 4;
			this.add(selectorPanel, c);
				
		}
		
		
		private void addBackgroundType() {
			
			materialList.add(new Grassland());
			materialList.add(new River());
			materialList.add(new Stone());
			
		}
		
		private void renderMaterial(int index) {
			
			selectorPanel = new JPanel();
			selectorPanel.setLayout(new GridBagLayout());

			buttonGroup = new ButtonGroup();
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(10, 5, 10, 5);
			
			c.gridx = 0;
			c.gridy = 0;
			this.add(nameField);
			
			JRadioButton radioButton = new JRadioButton();
			radioButton.setActionCommand("erase");
			radioButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					selectedMaterial = null;
					
				}
				
			});
			radioButton.setSelected(true);
			buttonGroup.add(radioButton);
			c.gridx = 0;
			c.gridy = 1;
			selectorPanel.add(radioButton, c);
			
			JLabel eraser = new JLabel("Eraser");
			c.gridx = 1;
			selectorPanel.add(eraser, c);
			
			
			for (int i = index; i < index + 5 && i < materialList.size(); i++) {
				
				radioButton = new JRadioButton();
				radioButton.setActionCommand(String.valueOf(i));
				radioButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int index = Integer.valueOf(e.getActionCommand());
						selectedMaterial = materialList.get(index);

					}
					
				});
				buttonGroup.add(radioButton);
				c.gridy = i - index + 2;
				c.gridx = 0;
				selectorPanel.add(radioButton, c);
				
				JLabel picture = new JLabel(new ImageIcon(materialList.get(i).getPicture().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				c.gridx = 1;
				selectorPanel.add(picture, c);
				
				JLabel materialName = new JLabel(materialList.get(i).toString());
				c.gridx = 2;
				selectorPanel.add(materialName, c);
				
			}
			
		}
		
	}
}
