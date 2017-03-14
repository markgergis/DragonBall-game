package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;


@SuppressWarnings("serial")
public class DragonBallChooseFighterView extends JFrame{
	
	public JPanel view;
	public JPanelWithBackground fightersView;
	public JLabel chooseAFighter;
	
	public DragonBallChooseFighterView(){
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		try {
			setContentPane(new JPanelWithBackground("ProjectFiles\\Pictures\\Choose Fighter Background.jpg"));
		} catch (IOException e) {
			getContentPane().setBackground(Color.black);
		}
		//getContentPane().setLayout(new BorderLayout());
		chooseAFighter = new JLabel("Choose a Fighter!");
		chooseAFighter.setVerticalAlignment(SwingConstants.TOP);
		chooseAFighter.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(chooseAFighter, BorderLayout.NORTH);
		
		view = new JPanel();
		view.setLayout(null);
		getContentPane().add(view, BorderLayout.CENTER);
		
		try {
			fightersView = new JPanelWithBackground(null);
		} catch (IOException e) {

		}
		//fightersView.setPreferredSize(new Dimension(800,700));
		fightersView.setBounds(17*getWidth()/64,5*getHeight()/108,15*getWidth()/32,25*getHeight()/36);
		view.setPreferredSize(new Dimension(getWidth()-20,20*getHeight()/27));
		view.setOpaque(false);
		fightersView.setOpaque(false);
		view.add(fightersView,BorderLayout.CENTER);
		//getContentPane().add(fightersView, BorderLayout.CENTER);
		
		//fightersView.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 250, 0)));
		
		
		setVisible(true);
		repaint();
		revalidate();
	}
	

	
	public class JPanelWithBackground extends JPanel {

	    private Image backgroundImage;
	
	    // Some code to initialize the background image.
	    // Here, we use the constructor to load the image. This
	    // can vary depending on the use case of the panel.
	    public JPanelWithBackground(String fileName) throws IOException {
	      if(fileName!=null)
	    	  backgroundImage = ImageIO.read(new File(fileName));
	      else
	    	  backgroundImage = null;
	    }
	    
	    public void rechangeImage(String s){
	    	if(s!= null)
	    	try {
				backgroundImage = ImageIO.read(new File(s));
			} catch (IOException e) {
				
			}
	    	else
	    		backgroundImage = null;
	    	repaint();
	    }
	
	    public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	
	      // Draw the background image.
	      g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
	    }
 	}
	
	

}
