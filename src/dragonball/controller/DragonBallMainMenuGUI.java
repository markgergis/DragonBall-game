package dragonball.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;




import dragonball.view.DragonBallMainMenuView;

@SuppressWarnings("serial")
public class DragonBallMainMenuGUI implements ActionListener , KeyListener{
	private DragonBallMainMenuView view;
	private JButton start;
	private JButton load;
	private JButton turnament;
	private JButton settings;
	private JButton exit;
	private JPanel panelB;
	private Keys key = new Keys();
	private GUIListener listener;
	
	public GUIListener getListener() {
		return listener;
	}
	public void setListener(GUIListener listener) {
		this.listener = listener;
	}
	JTextField t = new JTextField();
	
	public DragonBallMainMenuGUI() {
		view =new DragonBallMainMenuView();
		
		
		view.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		createButtons();
		view.setLayout(null);
		panelB = new JPanel();
		panelB.setLayout(new BoxLayout(panelB, BoxLayout.PAGE_AXIS));
		panelB.add(start);panelB.add(load);panelB.add(turnament);
		panelB.add(settings);panelB.add(exit);
		//panelB.setBounds(150, 200, 600, 1280);
		panelB.setBounds(5*view.getWidth()/64, 5*view.getHeight()/27, 5*view.getWidth()/16, 22*view.getHeight()/27);
		panelB.setOpaque(false);
		view.add(panelB);
		
		
		// using keyboard listener
		view.addKeyListener(this);
		panelB.addKeyListener(this);
		view.panel.addKeyListener(this);
		view.setVisible(true);
		
		
	}
	private void createButtons(){
		start = new JButton("Start");
		load = new JButton("Load");
		turnament = new JButton("Turnament");
		settings = new JButton("Settings");
		exit = new JButton("Exit");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("ProjectFiles\\Font\\TRIBTWO_.ttf")) ;
			font = font.deriveFont(Font.BOLD+font.ITALIC, view.getWidth()/24);
			start.setFont(font);start.setForeground(new Color(36, 68, 101));
			load.setFont(font);load.setForeground(new Color(36, 68, 101));
			turnament.setFont(font);turnament.setForeground(new Color(36, 68, 101));
			settings.setFont(font);settings.setForeground(new Color(36, 68, 101));
			exit.setFont(font);exit.setForeground(new Color(36, 68, 101));
		} catch (FontFormatException e) {
		} catch (IOException e) {	
		}
		start.addKeyListener(this);start.addActionListener(this);
		load.addKeyListener(this);load.addActionListener(this);
		turnament.addKeyListener(this);turnament.addActionListener(this);
		settings.addKeyListener(this);settings.addActionListener(this);
		exit.addKeyListener(this);exit.addActionListener(this);
		start.setBackground(Color.BLACK);start.setBorder(null);start.setOpaque(false);
		load.setBackground(Color.BLACK);load.setBorder(null);load.setOpaque(false);
		turnament.setBackground(Color.BLACK);turnament.setBorder(null);turnament.setOpaque(false);
		settings.setBackground(Color.BLACK);settings.setBorder(null);settings.setOpaque(false);
		exit.setBackground(Color.BLACK);exit.setBorder(null);exit.setOpaque(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(((JButton)ae.getSource()) == start)
		{
			JPanel f = new JPanel();
			f.setLayout(null);
			
			//f.setBounds(100, 400, 800, 400);
			f.setBounds(5*view.getWidth()/96, 10*view.getHeight()/27, 5*view.getWidth()/12, 10*view.getHeight()/27);
			view.getContentPane().add(f);
			JLabel l = new JLabel();
			JButton b = new JButton("Save");
			f.add(l);
			
			b.addActionListener(this);
			b.setBounds(300, 200, 150, 100);
			b.setFont(new Font("Script MT Bold", Font.BOLD,view.getWidth()/45));
			l.setBounds(50,0,750,100);
			l.setText("Please enter your name!");
			l.setFont(new Font("Times New Roman", Font.BOLD,view.getWidth()/32));
			
			t.setBounds(5*view.getWidth()/96, 10*view.getHeight()/27, 5*view.getWidth()/12, 5*view.getHeight()/27);
			//t.setBounds(100, 400, 800, 200);
			
			t.setFont(new Font("Times New Roman", Font.BOLD+Font.ITALIC,view.getWidth()/48));
			t.setOpaque(false);
			f.setVisible(true);
		
			f.setOpaque(false);
			
			panelB.setVisible(false);
			
			view.getContentPane().add(t);
			f.add(b);
		}
		if(((JButton)ae.getSource()).getText()=="Save")
		{
			if(t.getText().equals(""))
				t.setText("New_Player");
			if(listener!=null)
			listener.switchToChooseFighter(t.getText());
		}
		if(((JButton)ae.getSource()) == load)
		{
			if(listener!=null)
				listener.switchToWorld("load","", 'l');
		}
		if(((JButton)ae.getSource()) == exit)
		{
			System.exit(0);
		}
			
	}
	public DragonBallMainMenuView getGameView() {
		return view;
	}
	public void setGameView(DragonBallMainMenuView gameView) {
		this.view = gameView;
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			System.out.println("ana");
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
	
	
}
