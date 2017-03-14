package dragonball.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dragonball.model.attack.Attack;
import dragonball.model.battle.Battle;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.view.DragonBallBattleView;

public class DragonBallBattleGUI implements ActionListener ,KeyListener{

	public Battle battle;
	//public Game game;
	String attacks[];
	Attack chosen;
	JButton attack;
	JButton block ;
	JButton use ;
	public JPanel choose ;
	public DragonBallBattleView view;
	private GUIListener listener;
	
	public GUIListener getListener() {
		return listener;
	}
	public void setListener(GUIListener listener) {
		this.listener = listener;
	}
	@SuppressWarnings("serial")
	public DragonBallBattleGUI(Battle battle) {
		view = new DragonBallBattleView();
		
		this.battle = battle;
		
		//view.meInfo.setText("<html>Name: "+" me"+"<br/>HealthPoints: "+battle.getMe().getHealthPoints()+"<br/>Stamina: " + battle.getMe().getStamina()+ "<br/>Ki: "+ battle.getMe().getKi()+"</html>");
//		attacks =new String[1];
//		attacks[0] = "attaaaaaaaaaaaaak";
		attacks = new String[battle.getAssignedAttacks().size()];
		for (int i = 0; i < attacks.length; i++) {
			attacks[i] = battle.getAssignedAttacks().get(i).getName();
		}
		
		attack = new JButton(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\AttackIcon.png").getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
				
			}
		}; 
		attack.setBounds(5*view.getWidth()/32,5*view.getHeight()/36,5*view.getWidth()/96,5*view.getHeight()/54); 
		attack.addActionListener(this);
		
		block = new JButton(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\BlockIcon.png").getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
				
			}
		}; block.setBounds(15*view.getWidth()/32,5*view.getHeight()/36,5*view.getWidth()/96,5*view.getHeight()/54); block.addActionListener(this);
		
		use = new JButton(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\UseIcon.png").getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
				
			}
		}; use.setBounds(25*view.getWidth()/32,5*view.getHeight()/36,5*view.getWidth()/96,5*view.getHeight()/54); use.addActionListener(this);
		
		view.addKeyListener(this);
		
		choose = new JPanel();
		choose.setLayout(null);
		choose.addKeyListener(this);
		attack.addKeyListener(this);
		block.addKeyListener(this);
		use.addKeyListener(this);
//		view.chooseAttack = new JComboBox<String>(attacks);
//		//view.chooseAttack.setBounds(670,450,450,550);
//		view.chooseAttack.addActionListener(this);
//		view.chooseAttack.setVisible(true);
//		Font font;
//		try {
//			font = Font.createFont(Font.TRUETYPE_FONT, new File("ProjectFiles\\Font\\TRIBTWO_.ttf"));
//			font = font.deriveFont(Font.BOLD+Font.ITALIC, 30);
//			view.chooseAttack.setFont(font);
//			view.chooseAttack.setForeground(Color.YELLOW);
//		}
//		catch(FontFormatException e) {
//		} catch (IOException e) {
//		}
//		view.chooseAttack.setBackground(Color.CYAN);
//		choose.add(view.chooseAttack);
		choose.setOpaque(false);
		view.getContentPane().add(choose, BorderLayout.CENTER);
		choose.add(attack);
		choose.add(block);
		choose.add(use);
		
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(((JButton)ae.getSource()) == attack){
			choose.removeAll();choose.updateUI();
			choose = new JPanel();
			view.chooseAttack = new JComboBox(attacks);
			
			view.chooseAttack.setVisible(true);
			Font font;
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File("ProjectFiles\\Font\\TRIBTWO_.ttf"));
				font = font.deriveFont(Font.BOLD+Font.ITALIC, 30);
				view.chooseAttack.setFont(font);
				view.chooseAttack.setForeground(Color.YELLOW);
			}
			catch(FontFormatException e1) {
			} catch (IOException e1) {
			}
			view.chooseAttack.setBackground(Color.CYAN);
			choose.add(view.chooseAttack);
			choose.setOpaque(false);
			view.getContentPane().add(choose, BorderLayout.CENTER);
			view.chooseAttack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox combo = (JComboBox)e.getSource();
					try {
						chosen = getType((String)combo.getSelectedItem());
						battle.attack(chosen);
					} catch (NotEnoughKiException e1) {
						JOptionPane.showMessageDialog(view, e1.getMessage(),"Not Enough Ki",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
		}
		
		if(((JButton)ae.getSource()) == block){
			battle.block();
		}
		
		if(((JButton)ae.getSource()) == use){
			if(listener!=null)
				listener.use();
		}
		
		
	}
	
	private dragonball.model.attack.Attack getType(String s){
		for (int i = 0; i < battle.getAssignedAttacks().size(); i++) {
			if(battle.getAssignedAttacks().get(i).getName()==s)
				return battle.getAssignedAttacks().get(i);
		}
		return null;
		
	}
	
	public void rechoose(){
		choose.removeAll();choose.updateUI();
		choose = new JPanel();
		choose.setLayout(null);
		choose.setOpaque(false);
		view.getContentPane().add(choose, BorderLayout.CENTER);
		choose.add(attack);
		choose.add(block);
		choose.add(use);
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
