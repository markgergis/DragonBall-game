package dragonball.view;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


@SuppressWarnings("serial")
public class DragonBallDragonView extends JFrame{
	public JPanel panel;
	KeyListener keyListener;
	public JLabel chooseWish;
	
	@SuppressWarnings("static-access")
	public DragonBallDragonView() {
		setTitle("Dragon Ball");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setAlwaysOnTop (true);
		 
		
		try {
			setContentPane(panel = new JPanelWithBackground("ProjectFiles\\Pictures\\Dragon.jpg"));
		} catch (IOException e) {
			panel.setBackground(Color.black);
			setContentPane(panel);
		}
		
		chooseWish = new JLabel("Your Wish Is My Command!");
		chooseWish.setVerticalAlignment(SwingConstants.CENTER);
		chooseWish.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(chooseWish, BorderLayout.CENTER);
		
		
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("ProjectFiles\\Font\\TRIBTWO_.ttf"));
			font = font.deriveFont(Font.BOLD+font.ITALIC, getWidth()/48);
			chooseWish.setFont(font.deriveFont(Font.BOLD+font.ITALIC, 5*getWidth()/192));
			chooseWish.setForeground(Color.RED);
		}
		catch(FontFormatException e) {
		} catch (IOException e) {
		}
		
		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(95*getWidth()/96,5*getHeight()/27));
		panel.setOpaque(false);
		
		
		setVisible(true);
		repaint();
		revalidate();
	}
	
	class JPanelWithBackground extends JPanel {

		  private Image backgroundImage;

		  public JPanelWithBackground(String fileName) throws IOException {
		    backgroundImage = ImageIO.read(new File(fileName));
		  }

		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);

		    g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
		  }
		}
}
