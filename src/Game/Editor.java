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

/**
 * An editor that user can draw a map and save it.
 * @author huangzhangyu
 */
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
	private int xSize, ySize;  //Size in pixels.
	private MaterialSelector materialSelector;  //Frame for choosing type of material, eraser and save.
	private Material selectedMaterial;  //Material which selected in the selector.
	private int pictureSize = 50;  //Size in pixels.

	public Editor(int xSize, int ySize) {
				
		super("Map Editor");
		this.xSize = xSize;
		this.ySize = ySize;
		this.setSize(xSize, ySize + 29);  //The size of the frame should be 29 frame bigger than the map.
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		//ButtonLayer is used to check user's click events. Whenever a click event happen, the selected material will be put on the position of the activated button.
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
		
		//Create a white map.
		map = new Map(xSize, ySize, 50);
		renderBackground();
		pane.add(backgroundPanel, JLayeredPane.PALETTE_LAYER);
				
		renderBlock();
		pane.add(blockPanel, JLayeredPane.MODAL_LAYER);
		
		this.setLayeredPane(pane);
				
		materialSelector = new MaterialSelector();
		materialSelector.setVisible(true);
				
	}

	/**
	 * Render background panel. Each piece of background in the array will be print as an icon label on this panel with a black border. A white square will be printed if there has no block.
	 */
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
	
	/**
	 * Paints blocks in the array list onto block panel through override paint method.
	 */
	private void renderBlock() {
		
		blockPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < map.getBlocksSize(); i++) {
					Block block = map.getBlock(i);
					g.drawImage(block.getPicture(), block.getX() * 50, block.getY() * 50, this);  //Draw the image on position (x * 50, y * 50), the picture size is defined in block class.
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
		//Check the type of selected material.
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
			//If (x, y) has a block than first erase the block.
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
	
	/**
	 * Frame for choosing material that want to draw on the map.
	 * @author huangzhangyu
	 */
	private class MaterialSelector extends JFrame{
		
		private static final long serialVersionUID = 1L;
		private ArrayList<Material> materialList = new ArrayList<Material>();
		private JPanel selectorPanel;  //Show materials for selection.
		private ButtonGroup buttonGroup;  //A group of radio button.
		private JTextField nameField = new JTextField(10);  //The name of this map.
		
		public MaterialSelector() {
			
			super("Item Selector");
			this.setSize(200, 400);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setLayout(new GridBagLayout());
			
			//Place to save the map.
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
			
			addMaterialType();
			renderMaterial(0);
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 4;
			this.add(selectorPanel, c);
				
		}
		
		
		/**
		 * Add all of the material which can be draw on the map manually.
		 */
		private void addMaterialType() {
			
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
