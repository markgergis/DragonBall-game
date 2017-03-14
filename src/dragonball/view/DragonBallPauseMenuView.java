package dragonball.view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
@SuppressWarnings("serial")
public class DragonBallPauseMenuView extends JFrame{

	public JPanel panel;
	public JTextArea text;
	public DragonBallPauseMenuView(){
		//FullScreen
				setTitle("Pause Menu");
				setExtendedState(Frame.MAXIMIZED_BOTH);
				setUndecorated(true);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				panel = new JPanel();
				
				try {
					setContentPane(panel = new JPanelWithBackground("ProjectFiles\\Pictures\\pause.jpg"));
				} catch (IOException e) {
					panel.setBackground(Color.black);
					add(panel);
				}
				
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
				   // backgroundImage.getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_DEFAULT);
				  }

				  public void paintComponent(Graphics g) {
				    super.paintComponent(g);

				    // Draw the background image.
				    g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
				  }
				}
			

}
