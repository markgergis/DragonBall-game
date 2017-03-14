package dragonball.view;

import javax.imageio.ImageIO;
//import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;




@SuppressWarnings("serial")
public class DragonBallAdventuresView extends JFrame{
	KeyListener keyListener;
	public JPanel panel;
	public JTextArea text;
	public DragonBallAdventuresView(){
		//FullScreen
		setTitle("Dragon Ball");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		//setUndecorated(true);
		//setPreferredSize(new Dimension(MAXIMIZED_HORIZ,MAXIMIZED_VERT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//setVisible(true);
		
		//Image background = Toolkit.getDefaultToolkit().createImage("Background.png");
		panel = new JPanel();
		
		try {
			setContentPane(panel = new JPanelWithBackground("ProjectFiles\\Pictures\\Start Up_edited.jpg"));
		} catch (IOException e) {
			panel.setBackground(Color.black);
			add(panel);
		}
		panel.setPreferredSize(new Dimension(MAXIMIZED_HORIZ,MAXIMIZED_VERT));
		//getContentPane().setBounds(20, 20, 100, 100);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		//getContentPane().add(panel);
		//panel.setFocusable(true);
		
		//setResizable(false);
		repaint();
		revalidate();
		pack();
	}
	
	class JPanelWithBackground extends JPanel {

		  private Image backgroundImage;

		  // Some code to initialize the background image.
		  // Here, we use the constructor to load the image. This
		  // can vary depending on the use case of the panel.
		  public JPanelWithBackground(String fileName) throws IOException {
		    backgroundImage = ImageIO.read(new File(fileName));
		    backgroundImage.getScaledInstance(700,700, Image.SCALE_FAST);
		  }

		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);

		    // Draw the background image.
		    g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
		  }
		}
	
}
