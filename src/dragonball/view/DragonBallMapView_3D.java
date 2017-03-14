package dragonball.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;










import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class DragonBallMapView_3D extends JFrame{
	 public JPanel panel;
	 public JLabel label;
	 public JLabel label2;
	 public JLabel label3 ;
	 public JPanel chooseable;
	 public JPanel Map;
	 JPanel score;
 
 public DragonBallMapView_3D() {
	  //FullScreen
	  setTitle("Dragon Ball");
	  setExtendedState(Frame.MAXIMIZED_BOTH);
	  setUndecorated(true);
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setSize(Toolkit.getDefaultToolkit().getScreenSize());
	  panel = new JPanel();
	  
	  try {
	   setContentPane(panel = new JPanelWithBackground("ProjectFiles\\Pictures\\World-3D.jpg"));
	  } catch (IOException e) {
	   panel.setBackground(Color.black);
	   add(panel);
	  }
	  
	  Map = new JPanel();
	  Map.setBounds(0, getHeight()/2, getWidth(), getHeight()/2);
	  Map.setLayout(null);
	  Map.setOpaque(false);
	  getContentPane().add(Map);
	  
	  getContentPane().setLayout(null);
	  
	  score = new JPanel();
	  score.setBounds(0, 0, getWidth()/5, getHeight());
	  getContentPane().add(score);
	  score.setLayout(new BoxLayout(score, BoxLayout.PAGE_AXIS));
	  
	  JLabel PlayerInfo = new JLabel("Player's Info:");
	  PlayerInfo.setFont(new Font("Script MT Bold", Font.BOLD, getWidth()*40/1920));
	  PlayerInfo.setForeground(Color.white);
	  PlayerInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
	  score.add(PlayerInfo);
	  
	  label = new JLabel("");
	  label.setAlignmentX(Component.CENTER_ALIGNMENT);
	  label.setFont(new Font("Script MT Bold", Font.PLAIN,  getWidth()*25/1920));
	  label.setForeground(Color.white);
	  score.add(label);
	  
	  JLabel FighterInfo = new JLabel("Fighter's Info: ");
	  FighterInfo.setFont(new Font("Script MT Bold", Font.BOLD,  getWidth()*40/1920));
	  FighterInfo.setForeground(Color.white);
	  FighterInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
	  score.add(FighterInfo);
	  
	  label2 = new JLabel("");
	  label2.setAlignmentX(Component.CENTER_ALIGNMENT);
	  label2.setFont(new Font("Script MT Bold", Font.PLAIN,  getWidth()*25/1920));
	  label2.setForeground(Color.white);
	  score.add(label2);
	  
	  JLabel Collectibles = new JLabel("Collectibles:");
	  Collectibles.setFont(new Font("Script MT Bold", Font.BOLD,  getWidth()*40/1920));
	  Collectibles.setForeground(Color.white);
	  Collectibles.setAlignmentX(Component.CENTER_ALIGNMENT);
	  score.add(Collectibles);
	  
	  label3 = new JLabel("");
	  label3.setAlignmentX(Component.CENTER_ALIGNMENT);
	  label3.setFont(new Font("Script MT Bold", Font.PLAIN,  getWidth()*25/1920));
	  label3.setForeground(Color.white);
	  score.add(label3);
	  score.setOpaque(false);
	  
	  chooseable = new JPanel();
	  chooseable.setLayout(new BoxLayout(chooseable, BoxLayout.PAGE_AXIS));
	  chooseable.setOpaque(false);
	  chooseable.setBounds(35*getWidth()/48, 5*getHeight()/27, 5*getWidth()/24, 5*getHeight()/18);
	  getContentPane().add(chooseable);
	  repaint();
	  revalidate();
 }
 
 class JPanelWithBackground extends JPanel {

	    private Image backgroundImage;
	
	    // Some code to initialize the background image.
	    // Here, we use the constructor to load the image. This
	    // can vary depending on the use case of the panel.
	    public JPanelWithBackground(String fileName) throws IOException {
	      backgroundImage = ImageIO.read(new File(fileName));
	    }
	
	    public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	
	      // Draw the background image.
	      g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
	    }
 	}
}