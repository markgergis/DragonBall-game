package dragonball.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dragonball.view.DragonBallChooseFighterView;

public class DragonBallChooseFighterGUI implements ActionListener , KeyListener , MouseListener{
	private DragonBallChooseFighterView view;
	private String t;
	JButton earthling;
	JButton namekian;
	JButton saiyan;
	JButton majin;
	JButton frieza;
	JPanel panel;
	String x;
	JButton create;
	private JTextField text;
	
	private char c;
	
	private GUIListener listener;
	public GUIListener getListener() {
		return listener;
	}
	public void setListener(GUIListener listener) {
		this.listener = listener;
	}
	public DragonBallChooseFighterGUI(String t){
		this.t=t;
	
		view = new DragonBallChooseFighterView();
		
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		view.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(17*view.getWidth()/19,5*view.getHeight()/36));
		earthling = new JButton("Earthling");
		earthling.setMaximumSize(panel.getPreferredSize());
		earthling.addActionListener(this);
		earthling.addMouseListener(this);
		panel.add(earthling);
		
		
		
		
		namekian = new JButton("Namekian");
		namekian.setMaximumSize(panel.getPreferredSize());
		namekian.addActionListener(this);
		namekian.addMouseListener(this);
		panel.add(namekian);
		
		saiyan = new JButton("Saiyan");
		saiyan.setMaximumSize(panel.getPreferredSize());
		saiyan.addActionListener(this);
		saiyan.addMouseListener(this);
		panel.add(saiyan);
		
		majin = new JButton("Majin");
		majin.setMaximumSize(panel.getPreferredSize());
		majin.addActionListener(this);
		majin.addMouseListener(this);
		panel.add(majin);
		
		frieza = new JButton("Frieza");
		frieza.setMaximumSize(panel.getPreferredSize());
		frieza.addActionListener(this);
		frieza.addMouseListener(this);
		panel.add(frieza);
		
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("ProjectFiles\\Font\\TRIBTWO_.ttf"));
			font = font.deriveFont(Font.BOLD+font.ITALIC, view.getWidth()/48);
			earthling.setFont(font);earthling.setForeground(new Color(36, 68, 101));
			majin.setFont(font);majin.setForeground(new Color(36, 68, 101));
			saiyan.setFont(font);saiyan.setForeground(new Color(36, 68, 101));
			frieza.setFont(font);frieza.setForeground(new Color(36, 68, 101));
			namekian.setFont(font);namekian.setForeground(new Color(36, 68, 101));
			view.chooseAFighter.setFont(font.deriveFont(Font.BOLD+font.ITALIC, 5*view.getWidth()/192));view.chooseAFighter.setForeground(Color.RED);
			
		} catch (FontFormatException e) {
		} catch (IOException e) {
		}
		
		view.addKeyListener(this);
		
	}
	
	public void createNewPanel(){
		view.view.removeAll();
		view.view.updateUI();
		panel.removeAll();
		panel.updateUI();
		create= new JButton("Let's Create a Fighter");
		create.setFont(new Font("Script MT Bold", Font.BOLD,view.getWidth()/45));
		
		view.chooseAFighter.setText("Please enter fighter's name!");
		view.chooseAFighter.setFont(new Font("Script MT Bold", Font.BOLD,view.getWidth()/45));
		text = new JTextField("");
		text.setBounds(0, 10*view.getHeight()/27, 5*view.getWidth()/12, 5*view.getHeight()/27);
		view.chooseAFighter.setForeground(Color.WHITE);
		text.setForeground(Color.WHITE);
		
		text.setFont(new Font("Times New Roman", Font.BOLD+Font.ITALIC,view.getWidth()/55));
		text.setOpaque(false);
		
		view.view.add(text);
		panel.add(create);
		
		
		create.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if((JButton)ae.getSource() == create){
			if(text.getText().equals(""))
				text.setText("New_Fighter");
			if(listener!=null)
				listener.switchToWorld(t,text.getText(),c);
		}
		if(((JButton)ae.getSource()) == earthling)
		{
			c = 'E';
			createNewPanel();
		}
		if(((JButton)ae.getSource()) == saiyan)
		{
			c = 'S';
			createNewPanel();
		}
		if(((JButton)ae.getSource()) == frieza)
		{
			c = 'F';
			createNewPanel();
		}
		if(((JButton)ae.getSource()) == majin)
		{
			c = 'M';
			createNewPanel();
		}
		if(((JButton)ae.getSource()) == namekian)
		{
			c = 'N';
			createNewPanel();
		}
		
	}
	
	
	
	
	public DragonBallChooseFighterView getView() {
		return view;
	}
	public void setView(DragonBallChooseFighterView view) {
		this.view = view;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			
			if(listener!=null)
				listener.switchToPauseMenu();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(((JButton)e.getSource()) == earthling){
			view.fightersView.rechangeImage("ProjectFiles\\Pictures\\ChooseEarthling.png");
		}
		if(((JButton)e.getSource()) == saiyan){
			view.fightersView.rechangeImage("ProjectFiles\\Pictures\\Choosesaiyan.png");
		}
		if(((JButton)e.getSource()) == namekian){
			view.fightersView.rechangeImage("ProjectFiles\\Pictures\\ChooseNamekian.png");
		}
		if(((JButton)e.getSource()) == frieza){
			view.fightersView.rechangeImage("ProjectFiles\\Pictures\\ChooseFrieza.png");
		}
		if(((JButton)e.getSource()) == majin){
			view.fightersView.rechangeImage("ProjectFiles\\Pictures\\ChooseMajin.png");
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		view.fightersView.rechangeImage(null);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//	 class JPanelWithBackground extends JPanel {
//
//		    private Image backgroundImage;
//		
//		    // Some code to initialize the background image.
//		    // Here, we use the constructor to load the image. This
//		    // can vary depending on the use case of the panel.
//		    public JPanelWithBackground(String fileName) throws IOException {
//		      if(fileName!=null)
//		    	  backgroundImage = ImageIO.read(new File(fileName));
//		      else
//		    	  backgroundImage = null;
//		    }
//		    
//		    public void rechangeImage(String s){
//		    	if(s!= null)
//		    	try {
//					backgroundImage = ImageIO.read(new File(s));
//				} catch (IOException e) {
//					
//				}
//		    	else
//		    		backgroundImage = null;
//		    	repaint();
//		    }
//		
//		    public void paintComponent(Graphics g) {
//		      super.paintComponent(g);
//		
//		      // Draw the background image.
//		      g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
//		    }
//	 	}
}
