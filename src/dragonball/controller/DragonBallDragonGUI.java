package dragonball.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;




import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;





import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.view.DragonBallDragonView;


public class DragonBallDragonGUI implements ActionListener , KeyListener {
	public DragonBallDragonView view;
	JButton senzuBeans;
	JButton abilityPoints;
	JButton superAttack;
	JButton ultimateAttack;
	JPanel panel;
	public DragonWish wish;
	Dragon dragon;
	private GUIListener listener;


	public GUIListener getListener() {
		return listener;
	}

	public void setListener(GUIListener listener) {
		this.listener = listener;
	}

	public DragonBallDragonGUI(Dragon dragon) {
		view = new DragonBallDragonView();
		
		this.dragon = dragon;
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		view.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(4*view.getWidth()/5,2*view.getHeight()/3));
		panel.setVisible(true);
		
		senzuBeans = new JButton("Senzu Beans");
		senzuBeans.setMaximumSize(panel.getPreferredSize());
		senzuBeans.addActionListener(this);
		senzuBeans.setOpaque(false);senzuBeans.setBackground(Color.black);senzuBeans.setBorder(null);
		panel.add(senzuBeans);
		
		panel.add(Box.createRigidArea(panel.getPreferredSize()));
		abilityPoints = new JButton("Ability Points");
		abilityPoints.setMaximumSize(panel.getPreferredSize());
		abilityPoints.addActionListener(this);
		abilityPoints.setOpaque(false);abilityPoints.setBackground(Color.black);abilityPoints.setBorder(null);
		panel.add(abilityPoints);
		
		panel.add(Box.createRigidArea(panel.getPreferredSize()));
		panel.add(Box.createRigidArea(panel.getPreferredSize()));
		panel.add(Box.createRigidArea(panel.getPreferredSize()));
		// get the name of the super and ultimate attack by calling the method in chooseWish class
		superAttack = new JButton("Super Attack");
		superAttack.setMaximumSize(panel.getPreferredSize());
		superAttack.addActionListener(this);
		superAttack.setOpaque(false);superAttack.setBackground(Color.black);superAttack.setBorder(null);
		panel.add(superAttack);
		
		panel.add(Box.createRigidArea(panel.getPreferredSize()));
		ultimateAttack = new JButton("Ultimate Attack");
		ultimateAttack.setOpaque(false);ultimateAttack.setBackground(Color.black);ultimateAttack.setBorder(null);
		ultimateAttack.setMaximumSize(panel.getPreferredSize());
		ultimateAttack.addActionListener(this);
		panel.add(ultimateAttack);
		
		panel.setOpaque(false);
		
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("ProjectFiles\\Font\\TRIBTWO_.ttf")) ;
			font = font.deriveFont(Font.BOLD+font.ITALIC, 47);
			senzuBeans.setFont(font);senzuBeans.setForeground(new Color(36, 68, 101));
			abilityPoints.setFont(font);abilityPoints.setForeground(new Color(36, 68, 101));
			superAttack.setFont(font);superAttack.setForeground(new Color(36, 68, 101));
			ultimateAttack.setFont(font);ultimateAttack.setForeground(new Color(36, 68, 101));
		} catch (FontFormatException e) {
		} catch (IOException e) {	
		}
		view.setVisible(true);
		view.addKeyListener(this);
		
	}
	
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		DragonWish wishes[] = dragon.getWishes(); 
		if(((JButton)ae.getSource()) == senzuBeans){
			for (int i = 0; i < wishes.length; i++) {
				if(wishes[i].getType()==DragonWishType.SENZU_BEANS)
				{
					wish = wishes[i];
					break;
				}

			}
			
		}
		if(((JButton)ae.getSource()) == abilityPoints){
			for (int i = 0; i < wishes.length; i++) {
				if(wishes[i].getType()==DragonWishType.ABILITY_POINTS)
				{
					wish = wishes[i];
					break;
				}
			}
			
		}
		if(((JButton)ae.getSource()) == superAttack){
			for (int i = 0; i < wishes.length; i++) {
				if(wishes[i].getType()==DragonWishType.SUPER_ATTACK)
				{
					wish = wishes[i];
					break;
				}
			}
		}
		if(((JButton)ae.getSource()) == ultimateAttack){
			for (int i = 0; i < wishes.length; i++) {
				if(wishes[i].getType()==DragonWishType.ULTIMATE_ATTACK)
				{
					wish = wishes[i];
					break;
				}
			}
		}
		if(listener!=null)
			listener.switchToDragonWish(wish);
		
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
	
	
}
