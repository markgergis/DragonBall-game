package dragonball.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dragonball.view.DragonBallPauseMenuView;

public class DragonBallPauseMenuGUI implements  ActionListener {
	DragonBallPauseMenuView view;
	private JButton save;
	private JButton resume;
	private JButton exit;
	private JButton mainMenu;
	private JPanel buttons;
	private JTextField text;
	private GUIListener listener;
	
	public DragonBallPauseMenuGUI() {
		view = new DragonBallPauseMenuView();
		
		view.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(this);
		mainMenu.setBackground(Color.BLACK);mainMenu.setBorder(null);mainMenu.setOpaque(false);
		
		save = new JButton("save");
		save.addActionListener(this);
		save.setBackground(Color.BLACK);save.setBorder(null);save.setOpaque(false);
		
		resume = new JButton("resume");
		resume.addActionListener(this);
		resume.setBackground(Color.BLACK);resume.setBorder(null);resume.setOpaque(false);
		
		exit = new JButton("exit");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);exit.setBorder(null);exit.setOpaque(false);
		
		try {
			
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("ProjectFiles\\Font\\TRIBTWO_.ttf")) ;
			font = font.deriveFont(Font.BOLD+font.ITALIC, view.getWidth()/24);
			save.setFont(font);save.setForeground(new Color(0,0,0));
			resume.setFont(font);resume.setForeground(new Color(0,0,0));
			exit.setFont(font);exit.setForeground(new Color(0,0,0));
			mainMenu.setFont(font);mainMenu.setForeground(new Color(0,0,0));
			
		} catch (FontFormatException e) {
		} catch (IOException e) {	
		}
		
		view.setLayout(null);
		buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
		buttons.add(resume);buttons.add(save);buttons.add(mainMenu); buttons.add(exit);
		//buttons.setBounds(250, 150, 600, 1280);
		buttons.setBounds(25*view.getWidth()/192, 5*view.getHeight()/27, 5*view.getWidth()/16, 22*view.getHeight()/27);
		buttons.setOpaque(false);
		view.add(buttons);
		
		view.setVisible(true);
	}
	
	public GUIListener getListener() {
		return listener;
	}
	public void setListener(GUIListener listener) {
		this.listener = listener;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(((JButton)ae.getSource()) == save){
			listener.save();
		}
		if(((JButton)ae.getSource()) == resume){
			if(listener !=null)
				listener.resume();
		}
		if(((JButton)ae.getSource()) == exit){
			System.exit(0);
		}
		if(((JButton)ae.getSource()) == mainMenu)
		{
			System.out.println("ana");
			if(listener != null)
				listener.swichToMainMenu();
		}
	}
	
	
	
}
