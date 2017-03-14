package dragonball.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dragonball.model.attack.Attack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.game.Game;
import dragonball.view.DragonBallMapView_3D;


public class DragonBallWorldGUI_3D implements ActionListener , KeyListener{
	
	String type;
	String icon;
	
	private JButton assignAttack;
	private JButton upgrade;
	private JButton chooseActive;
	private JButton create;
	
	public JButton ok3;
	public JList<String> list;
	
	public JPanel fighter;
	public JPanel boss;
	 Game game;
	 public int xAxis;
	 public int yAxis;
	 public int width;
	 public int height;
	private DragonBallMapView_3D GUImap;
	GUIListener listener;
	Attack a = null;
	char chosen;
	JButton ok;
	public DragonBallWorldGUI_3D(Game g) {
		this(g.getPlayer().getName() , g.getPlayer().getActiveFighter().getName() , g.getPlayer().getActiveFighter().getType());
		}
	public DragonBallWorldGUI_3D(String name ,String n, char race) {
		try {
			game = new Game();
		} catch (IOException e) {
			
		}
		
		setType2(race);
		game.getPlayer().createFighter(race,n);
		game.getPlayer().setName(name);
		GUImap = new DragonBallMapView_3D();
		GUImap.setVisible(true);
		GUImap.label.setText("<html>Name: "+game.getPlayer().getName()+"<br/>Explored Maps: "+game.getPlayer().getExploredMaps()+"</html>");
		GUImap.label2.setText("<html>Name:"+game.getPlayer().getActiveFighter().getName()+"<br/>Type: "+type+"<br/>Level: "+game.getPlayer().getActiveFighter().getLevel()+"<br/>Target XP: "+game.getPlayer().getActiveFighter().getTargetXp()+"<br/> XP: "+  game.getPlayer().getActiveFighter().getXp()+"<br/>Max HealthPoints: "+game.getPlayer().getActiveFighter().getMaxHealthPoints()+"<br/>Physical Damage "+game.getPlayer().getActiveFighter().getPhysicalDamage()+"<br/>Blast Damage: "+game.getPlayer().getActiveFighter().getBlastDamage()+"<br/>Max KI "+game.getPlayer().getActiveFighter().getMaxKi()+"<br/>Max Stamina "+game.getPlayer().getActiveFighter().getMaxStamina()+"<br/>Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints()+"</html>");
		GUImap.label3.setText("<html>Senzu Beans: "+game.getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  game.getPlayer().getDragonBalls()+"</html>");GUImap.addKeyListener(this);
		
		fighter = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\"+icon).getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
			}
		};
		fighter.setBounds(xAxis = 43*GUImap.getWidth()/48,yAxis = 35*GUImap.getHeight()/108,width = 17*GUImap.getWidth()/192,height = 17*GUImap.getHeight()/108);
		fighter.setVisible(true);
		fighter.setOpaque(false);
		GUImap.Map.add(fighter);
		
		boss = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image backgroundImage = new ImageIcon("ProjectFiles\\Pictures\\vegeta.gif").getImage();
				g.drawImage(backgroundImage,0, 0,getWidth(),getHeight(), this);
			}
		};
		boss.setBounds(71*GUImap.getWidth()/384,GUImap.getHeight()/54,7*GUImap.getWidth()/192,7*GUImap.getHeight()/108);
		boss.setVisible(true);
		boss.setOpaque(false);
		GUImap.Map.add(boss);
		
		assignAttack = new JButton("Assign Attacks");
		upgrade = new JButton("Upgrade Active Fighter");
		chooseActive = new JButton("Choose Active Fighter");
		create = new JButton("Create New Fighter");
		
		assignAttack.addKeyListener(this);assignAttack.addActionListener(this);
		upgrade.addKeyListener(this);upgrade.addActionListener(this);
		chooseActive.addKeyListener(this);chooseActive.addActionListener(this);
		create.addKeyListener(this);create.addActionListener(this);
		
		assignAttack.setBorder(null);
		upgrade.setBorder(null);
		chooseActive.setBorder(null);
		create.setBorder(null);
		
		assignAttack.setFont(new Font("Script MT Bold", Font.BOLD,  GUImap.getWidth()/65));
		upgrade.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/65));
		chooseActive.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/65));
		create.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/65));
		
		assignAttack.setForeground(new Color(0,0,0));
		upgrade.setForeground(new Color(0,0,0));
		chooseActive.setForeground(new Color(0,0,0));
		create.setForeground(new Color(0,0,0));
		
		assignAttack.setAlignmentX(Component.CENTER_ALIGNMENT);
		upgrade.setAlignmentX(Component.CENTER_ALIGNMENT);
		chooseActive.setAlignmentX(Component.CENTER_ALIGNMENT);
		create.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		GUImap.chooseable.add(assignAttack);
		GUImap.chooseable.add(upgrade);
		GUImap.chooseable.add(chooseActive);
		GUImap.chooseable.add(create);

		GUImap.addKeyListener(this);
	}
	
	private void setType2(char race){
		switch (race) {
		case 'E':
			type = "Earthling";icon="EarthlingMap.gif";
			break;
		case 'S':
			type = "Saiyan";icon="SaiyanMap.gif";
			break;
		case 'N':
			type = "Namekian";icon="NamekianMap.gif";
			break;
		case 'F':
			type = "Frieza";icon="friezaMap.gif";
			break;
		case 'M':
			type = "Majin";icon="MajinMap.gif";
			break;
		}
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public DragonBallMapView_3D getGUImap() {
		return GUImap;
	}
	public void setGUImap(DragonBallMapView_3D gUImap) {
		GUImap = gUImap;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == upgrade){
			
			list = new JList<String>();
			list.setVisible(true);
			GUImap.chooseable.add(list);
			list.setAlignmentX(Component.CENTER_ALIGNMENT);
			ok = new JButton("OK!");
			GUImap.chooseable.add(ok);
			ok.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			assignAttack.setVisible(false);
			upgrade.setVisible(false);
			chooseActive.setVisible(false);
			create.setVisible(false);
			
			String[] x = {"Health Points","Blast Damage","Physical Damage","Ki","Stamina"};
			list.setListData(x);
			list.setFont(new Font("Script MT Bold", Font.BOLD,  GUImap.getWidth()/65));
			ok.addActionListener(this);
			
		}
		if(ae.getSource() == ok){
			if( list.getSelectedValue()==null){
				assignAttack.setVisible(true);
				upgrade.setVisible(true);
				chooseActive.setVisible(true);
				create.setVisible(true);
				list.setVisible(false);
				ok.setVisible(false);
				list = null;
				ok = null;
				return;
			}
			chosen = list.getSelectedValue().charAt(0);
			try {
				game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), chosen);
			} catch (NotEnoughAbilityPointsException e1) {
				JOptionPane.showMessageDialog(GUImap, e1.getMessage(),"Not enough ability points",
					    JOptionPane.ERROR_MESSAGE);
			}
			assignAttack.setVisible(true);
			upgrade.setVisible(true);
			chooseActive.setVisible(true);
			create.setVisible(true);
			list.setVisible(false);
			ok.setVisible(false);
			list = null;
			ok = null;
			GUImap.label2.setText("<html>Name:"+game.getPlayer().getActiveFighter().getName()+"<br/>Type: "+type+"<br/>Level: "+game.getPlayer().getActiveFighter().getLevel()+"<br/>Target XP: "+game.getPlayer().getActiveFighter().getTargetXp()+"<br/> XP: "+  game.getPlayer().getActiveFighter().getXp()+"<br/>Max HealthPoints: "+game.getPlayer().getActiveFighter().getMaxHealthPoints()+"<br/>Physical Damage "+game.getPlayer().getActiveFighter().getPhysicalDamage()+"<br/>Blast Damage: "+game.getPlayer().getActiveFighter().getBlastDamage()+"<br/>Max KI "+game.getPlayer().getActiveFighter().getMaxKi()+"<br/>Max Stamina "+game.getPlayer().getActiveFighter().getMaxStamina()+"<br/>Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints()+"</html>");
			GUImap.label3.setText("<html>Senzu Beans: "+game.getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  game.getPlayer().getDragonBalls()+"</html>");GUImap.addKeyListener(this);
			
			GUImap.revalidate();
			GUImap.repaint();
		}
		
		if(ae.getSource() == create){
			
			if(listener!=null)
			{
				listener.switchToChooseFighter("");
				GUImap.setVisible(false);
			}
			GUImap.label2.setText("<html>Name:"+game.getPlayer().getActiveFighter().getName()+"<br/>Type: "+type+"<br/>Level: "+game.getPlayer().getActiveFighter().getLevel()+"<br/>Target XP: "+game.getPlayer().getActiveFighter().getTargetXp()+"<br/> XP: "+  game.getPlayer().getActiveFighter().getXp()+"<br/>Max HealthPoints: "+game.getPlayer().getActiveFighter().getMaxHealthPoints()+"<br/>Physical Damage "+game.getPlayer().getActiveFighter().getPhysicalDamage()+"<br/>Blast Damage: "+game.getPlayer().getActiveFighter().getBlastDamage()+"<br/>Max KI "+game.getPlayer().getActiveFighter().getMaxKi()+"<br/>Max Stamina "+game.getPlayer().getActiveFighter().getMaxStamina()+"<br/>Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints()+"</html>");
			GUImap.label3.setText("<html>Senzu Beans: "+game.getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  game.getPlayer().getDragonBalls()+"</html>");GUImap.addKeyListener(this);

		}
		
		
		if(((JButton)ae.getSource()) == assignAttack)
		{
			String[] s = new String[2+game.getPlayer().getSuperAttacks().size()+game.getPlayer().getUltimateAttacks().size() + 1];
			s[0] = "*Super Attack*";
			for (int i = 1; i < game.getPlayer().getSuperAttacks().size()+1; i++) {
				s[i] = game.getPlayer().getSuperAttacks().get(i-1).getName();
			}
			s[game.getPlayer().getSuperAttacks().size()+1]="=============";
			s[game.getPlayer().getSuperAttacks().size()+2]="*Ultimate Attacks";
			for (int i = game.getPlayer().getSuperAttacks().size()+3; i < s.length; i++) {
				s[i] = game.getPlayer().getUltimateAttacks().get(i-(game.getPlayer().getSuperAttacks().size()+3)).getName();
			}
			list = new JList<String>(s);
			list.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/80));
			list.setAlignmentX(Component.CENTER_ALIGNMENT);
			ok3 = new JButton("Ok!");
			ok3.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/60));
			ok3.setAlignmentX(Component.CENTER_ALIGNMENT);
			ok3.addActionListener(this);
			ok3.addKeyListener(this);
			GUImap.chooseable.removeAll();GUImap.chooseable.updateUI();
			GUImap.chooseable.add(list);
			GUImap.chooseable.add(ok3);
		}
		
		
		if(((JButton)ae.getSource()) == ok3){
			
			for (int i = 0; i < game.getPlayer().getSuperAttacks().size(); i++) {
				if(list.getSelectedValue() == game.getPlayer().getSuperAttacks().get(i).getName())
					a = game.getPlayer().getSuperAttacks().get(i);
			}
			if(a !=null){
				String[] s2 = new String[game.getPlayer().getActiveFighter().getSuperAttacks().size() + 1];
				s2[0] = "Do not replace!";
				for (int i = 1; i < game.getPlayer().getActiveFighter().getSuperAttacks().size()+1; i++) {
					s2[i] = game.getPlayer().getActiveFighter().getSuperAttacks().get(i-1).getName();
				}
				JList<String> old = new JList<String>(s2);
				old.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/80));
				old.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				GUImap.chooseable.remove(list);
				GUImap.chooseable.remove(ok3);
				GUImap.chooseable.updateUI();
				GUImap.chooseable.add(old);
				JButton ok2 = new JButton("Ok!");
				
				GUImap.chooseable.add(ok2);
				ok2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						SuperAttack b = null;
						for (int i = 0; i < game.getPlayer().getActiveFighter().getSuperAttacks().size(); i++) {
							if(list.getSelectedValue() == game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName())
								b = game.getPlayer().getActiveFighter().getSuperAttacks().get(i);
						}
						try {
							GUImap.chooseable.removeAll();GUImap.chooseable.updateUI();
							GUImap.chooseable.add(assignAttack);
							GUImap.chooseable.add(upgrade);
							GUImap.chooseable.add(chooseActive);
							GUImap.chooseable.add(create);
							if(a!=null)
								game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(),(SuperAttack) a, b);
							
							a = null;
							
						} catch (DuplicateAttackException e1) {
							JOptionPane.showMessageDialog(GUImap, e1.getMessage(),"DuplicateAttackException",
								    JOptionPane.ERROR_MESSAGE);
						} catch (MaximumAttacksLearnedException e1) {
							JOptionPane.showMessageDialog(GUImap, e1.getMessage(),"MaximumAttacksLearnedException",
								    JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}
			else
			{
				for (int i = 0; i < game.getPlayer().getUltimateAttacks().size(); i++) {
					if(list.getSelectedValue() == game.getPlayer().getUltimateAttacks().get(i).getName())
						a = game.getPlayer().getUltimateAttacks().get(i);
				}
				String[] s2 = new String[game.getPlayer().getActiveFighter().getUltimateAttacks().size() + 1];
				s2[0] = "Do not replace!";
				System.out.println(game.getPlayer().getActiveFighter().getUltimateAttacks().size());
				for (int i = 1; i < game.getPlayer().getActiveFighter().getUltimateAttacks().size()+1; i++) {
					s2[i] = game.getPlayer().getActiveFighter().getUltimateAttacks().get(i-1).getName();
				}
				JList<String> old = new JList<String>(s2);
				old.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/80));
				old.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				GUImap.chooseable.remove(list);
				GUImap.chooseable.remove(ok3);
				GUImap.chooseable.updateUI();
				GUImap.chooseable.add(old);
				JButton ok2 = new JButton("Ok!");
				
				GUImap.chooseable.add(ok2);
				ok2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						UltimateAttack b = null;
						for (int i = 0; i < game.getPlayer().getActiveFighter().getUltimateAttacks().size(); i++) {
							if(list.getSelectedValue() == game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName())
								b = game.getPlayer().getActiveFighter().getUltimateAttacks().get(i);
						}
						
							try {
								GUImap.chooseable.removeAll();GUImap.chooseable.updateUI();
								GUImap.chooseable.add(assignAttack);
								GUImap.chooseable.add(upgrade);
								GUImap.chooseable.add(chooseActive);
								GUImap.chooseable.add(create);
								if(a!=null)
									game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(),(UltimateAttack) a, b);
								
								a = null;
								
							} catch (NotASaiyanException e1) {
								JOptionPane.showMessageDialog(GUImap, e1.getMessage(),"NotASaiyanException",
									    JOptionPane.ERROR_MESSAGE);
							} catch (DuplicateAttackException e1) {
								JOptionPane.showMessageDialog(GUImap, e1.getMessage(),"DuplicateAttackException",
									    JOptionPane.ERROR_MESSAGE);
							} catch (MaximumAttacksLearnedException e1) {
								JOptionPane.showMessageDialog(GUImap, e1.getMessage(),"MaximumAttacksLearnedException",
									    JOptionPane.ERROR_MESSAGE);
							}
						
					}
				});
			}
			
			GUImap.label2.setText("<html>Name:"+game.getPlayer().getActiveFighter().getName()+"<br/>Type: "+type+"<br/>Level: "+game.getPlayer().getActiveFighter().getLevel()+"<br/>Target XP: "+game.getPlayer().getActiveFighter().getTargetXp()+"<br/> XP: "+  game.getPlayer().getActiveFighter().getXp()+"<br/>Max HealthPoints: "+game.getPlayer().getActiveFighter().getMaxHealthPoints()+"<br/>Physical Damage "+game.getPlayer().getActiveFighter().getPhysicalDamage()+"<br/>Blast Damage: "+game.getPlayer().getActiveFighter().getBlastDamage()+"<br/>Max KI "+game.getPlayer().getActiveFighter().getMaxKi()+"<br/>Max Stamina "+game.getPlayer().getActiveFighter().getMaxStamina()+"<br/>Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints()+"</html>");
			GUImap.label3.setText("<html>Senzu Beans: "+game.getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  game.getPlayer().getDragonBalls()+"</html>");GUImap.addKeyListener(this);

		}
		
		if(((JButton)ae.getSource()) == chooseActive)
		{
			String s[] = new String[game.getPlayer().getFighters().size()];
			for (int i = 0; i < s.length; i++) {
				s[i] = game.getPlayer().getFighters().get(i).getName();
			}
			
			list = new JList<String>(s);
			list.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/80));
			list.setAlignmentX(Component.CENTER_ALIGNMENT);
			JButton ok2 = new JButton("Ok!");
			ok2.setFont(new Font("Script MT Bold", Font.BOLD, GUImap.getWidth()/60));
			ok2.setAlignmentX(Component.CENTER_ALIGNMENT);
			ok2.addActionListener(this);

			GUImap.chooseable.removeAll();GUImap.chooseable.updateUI();
			GUImap.chooseable.add(list);
			GUImap.chooseable.add(ok2);
			ok2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					PlayableFighter f = null;
					for (int i = 0; i < game.getPlayer().getFighters().size(); i++) {
						if(list.getSelectedValue() == game.getPlayer().getFighters().get(i).getName())
							f = game.getPlayer().getFighters().get(i);
					}
					
					game.getPlayer().setActiveFighter(f);
					
					
					GUImap.chooseable.removeAll();GUImap.chooseable.updateUI();
					
					GUImap.chooseable.add(assignAttack);
					GUImap.chooseable.add(upgrade);
					GUImap.chooseable.add(chooseActive);
					GUImap.chooseable.add(create);
					setType2(f.getType());
//					((JLabel) GUImap.Map.getComponents()[loc]).setIcon(new ImageIcon("ProjectFiles\\Pictures\\"+icon));
					GUImap.label2.setText("<html>Name:"+game.getPlayer().getActiveFighter().getName()+"<br/>Type: "+type+"<br/>Level: "+game.getPlayer().getActiveFighter().getLevel()+"<br/>Target XP: "+game.getPlayer().getActiveFighter().getTargetXp()+"<br/> XP: "+  game.getPlayer().getActiveFighter().getXp()+"<br/>Max HealthPoints: "+game.getPlayer().getActiveFighter().getMaxHealthPoints()+"<br/>Physical Damage "+game.getPlayer().getActiveFighter().getPhysicalDamage()+"<br/>Blast Damage: "+game.getPlayer().getActiveFighter().getBlastDamage()+"<br/>Max KI "+game.getPlayer().getActiveFighter().getMaxKi()+"<br/>Max Stamina "+game.getPlayer().getActiveFighter().getMaxStamina()+"<br/>Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints()+"</html>");
					GUImap.label3.setText("<html>Senzu Beans: "+game.getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  game.getPlayer().getDragonBalls()+"</html>");
				}
			});
			//ok.addKeyListener(this);
			GUImap.label2.setText("<html>Name:"+game.getPlayer().getActiveFighter().getName()+"<br/>Type: "+type+"<br/>Level: "+game.getPlayer().getActiveFighter().getLevel()+"<br/>Target XP: "+game.getPlayer().getActiveFighter().getTargetXp()+"<br/> XP: "+  game.getPlayer().getActiveFighter().getXp()+"<br/>Max HealthPoints: "+game.getPlayer().getActiveFighter().getMaxHealthPoints()+"<br/>Physical Damage "+game.getPlayer().getActiveFighter().getPhysicalDamage()+"<br/>Blast Damage: "+game.getPlayer().getActiveFighter().getBlastDamage()+"<br/>Max KI "+game.getPlayer().getActiveFighter().getMaxKi()+"<br/>Max Stamina "+game.getPlayer().getActiveFighter().getMaxStamina()+"<br/>Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints()+"</html>");
			GUImap.label3.setText("<html>Senzu Beans: "+game.getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  game.getPlayer().getDragonBalls()+"</html>");GUImap.addKeyListener(this);
			GUImap.revalidate();
			GUImap.repaint();
		}
		GUImap.addKeyListener(this);
		GUImap.revalidate();
		GUImap.repaint();
		GUImap.setFocusable(true);
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			if(listener!=null)
				listener.switchToPauseMenu();
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			game.getPlayer().callDragon();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{

			try
			{
				game.getWorld().moveUp();
				fighter.setBounds((xAxis-=((-7*GUImap.getWidth()/384)+(game.getWorld().getPlayerColumn())*7*GUImap.getWidth()/1920)),(yAxis-=(29*GUImap.getHeight()/540-(9-(game.getWorld().getPlayerRow()+1))*GUImap.getHeight()/216)),(width-=1*GUImap.getWidth()/192),(height-=1*GUImap.getHeight()/108));
			}
			catch(MapIndexOutOfBoundsException ex){
				return;
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			

			try
			{
				game.getWorld().moveDown();
				fighter.setBounds((xAxis+=((-7*GUImap.getWidth()/384)+(game.getWorld().getPlayerColumn())*7*GUImap.getWidth()/1920)),(yAxis+=(29*GUImap.getHeight()/540-(9-(game.getWorld().getPlayerRow()))*GUImap.getHeight()/216)),(width+=1*GUImap.getWidth()/192),(height+=1*GUImap.getHeight()/108));

			}
			catch(MapIndexOutOfBoundsException ex){
				return;
			}

			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{

			try
			{
				game.getWorld().moveLeft();
				fighter.setBounds((xAxis-=(19*GUImap.getWidth()/192)-(GUImap.getWidth()/240)*(9-game.getWorld().getPlayerRow())),yAxis,width,height);

			}
			catch(MapIndexOutOfBoundsException ex){
				return;
			}

			
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{

			try
			{
				game.getWorld().moveRight();
				fighter.setBounds((xAxis+=(19*GUImap.getWidth()/192)-(GUImap.getWidth()/240)*(9-game.getWorld().getPlayerRow())),yAxis,width,height);

			}
			catch(MapIndexOutOfBoundsException ex){
				return;
			}
			
			
		}
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
}
