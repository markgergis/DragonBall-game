package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;





import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;


@SuppressWarnings("serial")
public class DragonBallBattleView extends JFrame{

	public JPanel fighters;
	public JPanelWithBackground me;
	public JPanelWithBackground foe;
	public JPanel view;
	public JComboBox chooseAttack;
	public JPanel info;
	public JPanel infoImg;
	public JProgressBar meInfo;
	public JProgressBar foeInfo;
	public JLabel turnMe;
	public JLabel turnFoe;
	public JPanel meBlock;
	public JPanel foeBlock;
	public JProgressBar meKi;
	public JProgressBar foeKi;
	public JProgressBar meStamina;
	public JProgressBar foeStamina;
	public JLabel meSenzo;
	String gifs[] = {"Battle (1).gif","Battle (2).gif","Battle (3).gif","Battle (4).gif","Battle (5).gif","Battle (6).gif"};
	String gifs2[] = {"foe (1).gif","foe (2).gif","foe (3).gif","foe (4).gif"};
	public JLabel meKiM;
	public JLabel foeKiM;
	public JLabel meStaminaM;
	public JLabel foeStaminaM;
	public JLabel transformed;
	
	public DragonBallBattleView(){
		//FullScreen
		setTitle("Battle");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		Random r = new Random();
		try {
			
			setContentPane(new JPanelWithBackground("ProjectFiles\\Pictures\\"+gifs[r.nextInt(gifs.length)]));
		} catch (IOException e) {
			getContentPane().setBackground(Color.black);
		}
		getContentPane().setLayout(new BorderLayout(0,0));
//		view = new JPanel();
//		view.setLayout(new BoxLayout(view, BoxLayout.X_AXIS));
//		getContentPane().add(view, BorderLayout.CENTER);
//		view.setPreferredSize(new Dimension(getWidth(),getHeight()/4));
		fighters = new JPanel();
		
		try {
			me = new JPanelWithBackground(null);
		} catch (IOException e) {
		}
		try {
			foe = new JPanelWithBackground("ProjectFiles\\Pictures\\"+gifs2[r.nextInt(gifs2.length)]);
		} catch (IOException e) {
			
		}
			
//		me.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
//		foe.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		//fighters.setLayout(new BoxLayout(fighters, BoxLayout.X_AXIS));
		fighters.setLayout(null);
		fighters.setOpaque(false);
		me.setVisible(true);
		me.setOpaque(false);
		fighters.setPreferredSize(new Dimension(4*getWidth()/5,1*getHeight()/3));
		me.setBounds(65*getWidth()/96,0,5*getWidth()/32,5*getHeight()/18);
		foe.setBounds(5*getWidth()/32,0,5*getWidth()/32,5*getHeight()/18);
		foe.setOpaque(false);
		
		
		meBlock  = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\icon-safety.png").getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
			}
		};
		
		foeBlock = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\icon-safety.png").getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
			}
		};
		foeBlock.setBounds(5*getWidth()/24,getHeight()/9,5*getWidth()/96,5*getHeight()/54);
		meBlock.setBounds(45*getWidth()/60,getHeight()/9,5*getWidth()/96,5*getHeight()/54);
		meBlock.setOpaque(false);
		foeBlock.setOpaque(false);
		meBlock.setVisible(false);
		foeBlock.setVisible(false);
		fighters.add(meBlock);
		fighters.add(foeBlock);
		fighters.add(foe);
		fighters.add(me);
		
		getContentPane().add(fighters, BorderLayout.SOUTH);
//		view.add(me);
//		view.add(foe,BorderLayout.EAST);
		fighters.setVisible(true);
		
		
		
		
		info = new JPanel();
		info.setLayout(null);
		info.setPreferredSize(new Dimension(9*getWidth()/10,1*getHeight()/4));
		getContentPane().add(info, BorderLayout.NORTH);

		info.setOpaque(false);
		turnMe = new JLabel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\arrow.png").getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
			}
		};
		
		 turnFoe = new JLabel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\arrow.png").getImage();
				g.drawImage(backgroundImage,0+getWidth(), 0,-getWidth(),getHeight(), this);
				
				
			}
		};
		turnMe.setBounds(23*getWidth()/48,getHeight()/18,getWidth()/24,2*getHeight()/27);
		turnFoe.setBounds(23*getWidth()/48,getHeight()/18,getWidth()/24,2*getHeight()/27);
		turnMe.setVisible(true);
		turnFoe.setVisible(false);
		
		
		
		foeInfo = new JProgressBar();
		foeInfo.setValue(100);
		foeInfo.setBounds(11*getWidth()/64, 83*getHeight()/1080, 13*getWidth()/48, 17*getHeight()/540);
		foeInfo.setForeground(Color.GREEN);
		foeInfo.setBackground(Color.RED);
		
		foeKi = new JProgressBar();
		foeKi.setMaximum(8);
		foeKi.setValue(8);
		foeKi.setBounds(139*getWidth()/384, 5*getHeight()/108, 83*getWidth()/960, 17*getHeight()/540);
		foeKi.setForeground(Color.ORANGE);
		foeKi.setBackground(Color.DARK_GRAY);
		
		foeKiM = new JLabel("Max Ki  ");
		foeKiM.setBounds(400,20,200,80); foeKiM.setFont(new Font("Segoe UI Semibold", Font.BOLD, getWidth()/50));
		foeKiM.setForeground(Color.RED);
		
		transformed = new JLabel("Transformed");
		transformed.setBounds(getWidth()/2,0,5*getWidth()/48,5*getHeight()/80); transformed.setFont(new Font("Segoe UI Semibold", Font.BOLD, getWidth()/50));
		transformed.setForeground(Color.RED);
		transformed.setVisible(false);
		
		foeStamina = new JProgressBar();
		foeStamina.setMaximum(8);
		foeStamina.setValue(8);
		foeStamina.setBounds(65*getWidth()/384, 23*getHeight()/216, 55*getWidth()/384, 7*getHeight()/108);
		foeStamina.setForeground(Color.YELLOW);
		foeStamina.setBackground(Color.DARK_GRAY);
		
		foeStaminaM = new JLabel("Max Stamina  ");
		foeStaminaM.setBounds(65*getWidth()/384, 13*getHeight()/108, 300, 7*getHeight()/108);
		foeStaminaM.setFont(new Font("Segoe UI Semibold", Font.BOLD, getWidth()/50));
		foeStaminaM.setForeground(Color.RED);
		
		meInfo = new JProgressBar();
		meInfo.setValue(0);
		meInfo.setBounds(107*getWidth()/192, 83*getHeight()/1080, 13*getWidth()/48, 17*getHeight()/540);
		meInfo.setForeground(Color.RED);
		meInfo.setBackground(Color.GREEN);
		
		meKi = new JProgressBar();
		meKi.setMaximum(8);
		meKi.setValue(0);
		meKi.setBounds(353*getWidth()/640, 5*getHeight()/108, 83*getWidth()/960, 17*getHeight()/540);
		meKi.setForeground(Color.DARK_GRAY);
		meKi.setBackground(Color.ORANGE);
		
		meKiM = new JLabel("Max Ki  ");
		meKiM.setBounds(1400,20,200,80); meKiM.setFont(new Font("Segoe UI Semibold", Font.BOLD, getWidth()/50));
		meKiM.setForeground(Color.RED);
		
		meStamina = new JProgressBar();
		meStamina.setMaximum(8);
		meStamina.setValue(0);
		meStamina.setBounds(11*getWidth()/16, 23*getHeight()/216, 55*getWidth()/384, 7*getHeight()/108);
		meStamina.setForeground(Color.DARK_GRAY);
		meStamina.setBackground(Color.YELLOW);
		
		meStaminaM = new JLabel("Max Stamina  ");
		meStaminaM.setBounds(271*getWidth()/384, 13*getHeight()/108, 300, 7*getHeight()/108);
		meStaminaM.setFont(new Font("Segoe UI Semibold", Font.BOLD, getWidth()/50));
		meStaminaM.setForeground(Color.RED);
		
		meSenzo = new JLabel("  "){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\Senzo.png").getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
				
				
			}
		};meSenzo.setBounds(1240,130,80,80); meSenzo.setFont(new Font("Segoe UI Semibold", Font.BOLD, getWidth()/50));
		meSenzo.setForeground(Color.green);
		
		info.add(turnMe);

		try {
			infoImg = new JPanelWithBackground("ProjectFiles\\Pictures\\Battle.png");
		} catch (IOException e1) {
		}
		infoImg.setBounds(getWidth()/20,0,9*getWidth()/10,1*getHeight()/4);
		infoImg.setOpaque(false);
		//getContentPane().add(infoImg, BorderLayout.NORTH);
		
		info.add(meStaminaM);
		info.add(foeStaminaM);
		info.add(foeKiM);
		info.add(meKiM);
		info.add(meSenzo);
		info.add(infoImg);
		info.add(meInfo);
		info.add(foeInfo);
		
		info.add(foeKi);
		info.add(meKi);
		info.add(foeStamina);
		info.add(meStamina);
		
		
		info.setVisible(true);
		
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
		    backgroundImage = new ImageIcon(fileName).getImage();
		  }
		  
		  public void rechangeImage(String s){
		    		backgroundImage = new ImageIcon(s).getImage();
		    	repaint();
		    }

		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);

		    // Draw the background image.
		    g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
		  }
		}
	
	
	
}
