package Game;

import javax.swing.*;  
import java.awt.*;  
public class TestJLayeredPane extends JFrame {  
  public TestJLayeredPane() {  
    super("LayeredPane Example");  
    setSize(200, 200);  
    JLayeredPane pane = getLayeredPane();  
    //creating buttons  
    JButton top = new JButton();  
    top.setBackground(Color.white);  
    top.setBounds(20, 20, 50, 50);  
    JButton middle = new JButton();  
    middle.setBackground(Color.red);  
    middle.setBounds(40, 40, 50, 50);  
    JButton bottom = new JButton();  
    bottom.setBackground(Color.cyan);  
    bottom.setBounds(60, 60, 50, 50);  
    //adding buttons on pane  

    pane.add(bottom, 1);  
    pane.add(middle, new Integer(2));  
    pane.add(top, new Integer(3));  
  }  
  public static void main(String[] args) {  
      TestJLayeredPane panel = new  TestJLayeredPane();  
      panel.setVisible(true);  
  }  
}  