package dragonball.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;









import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class DragonBallMapView extends JFrame{
 public JPanel panel;
 public JPanel Map;
 public JLabel label;
 public JLabel label2;
 public JLabel label3 ;
 public JPanel chooseable;
 JPanel score;
 
 public DragonBallMapView() {
  //FullScreen
  setTitle("Dragon Ball");
  setExtendedState(Frame.MAXIMIZED_BOTH);
  setUndecorated(true);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  setSize(Toolkit.getDefaultToolkit().getScreenSize());
  panel = new JPanel();
  
  try {
   setContentPane(panel = new JPanelWithBackground("ProjectFiles\\Pictures\\WorldMap.jpg"));
  } catch (IOException e) {
   panel.setBackground(Color.black);
   add(panel);
  }
  
  
  getContentPane().setLayout(null);
  
  Map = new JPanel(new GridLayout(10,10));
  Map.setBounds(1*getWidth()/5,0,4*getWidth()/5, getHeight());
  
  getContentPane().add(Map);
  Map.setOpaque(false);
  
  Map.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

  for (int i =0; i<10; i++){
   for (int j = 0; j < 10; j++) {
      final JLabel newCell = new JLabel();
      newCell.setName(""+i+" "+j);
      newCell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      Map.add(newCell);
   }
  }
  
  score = new JPanel();
  score.setBounds(0, 0, 1*getWidth()/5, getHeight());
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
  chooseable.setLayout(new BoxLayout(chooseable, BoxLayout.Y_AXIS));
  chooseable.setOpaque(false);
  chooseable.setAlignmentX(Component.CENTER_ALIGNMENT);
  score.add(Box.createRigidArea(new Dimension(score.getWidth(), score.getHeight()/5)));
  score.add(chooseable, BorderLayout.SOUTH);
  
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