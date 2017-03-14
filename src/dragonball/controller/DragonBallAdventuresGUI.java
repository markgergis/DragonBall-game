package dragonball.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;



import javax.swing.JOptionPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;

@SuppressWarnings("serial")
public class DragonBallAdventuresGUI implements GameListener , GUIListener{

	DragonBallBattleGUI battle;
	DragonBallDragonGUI dragon;
	DragonBallChooseFighterGUI chooseFighter;
	DragonBallMainMenuGUI mainMenu;
	DragonBallPauseMenuGUI pauseMenu;
	DragonBallWorldGUI_3D world;
	Clip clip;
	private void playStart(){
		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File("ProjectFiles\\Music\\Start Up music.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			System.out.println("Error with playing sound.");
	        e.printStackTrace();
		}

		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	private void stop(){
		clip.stop();
	}

	public DragonBallAdventuresGUI() {
			mainMenu = new DragonBallMainMenuGUI();
			mainMenu.setListener(this);
			playStart();
	}
	
	
	
	@Override
	public void onDragonCalled(Dragon dragon) {		
			switchToDragon(dragon);
		
		}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		   world.getGUImap().label3.setText("<html>Senzu Beans: "+world.getGame().getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  world.getGame().getPlayer().getDragonBalls()+"</html>");
		   world.getGUImap().setVisible(true);
			
	}

	@Override
	public void onBattleEvent(BattleEvent e) {
		if(e.getType() == BattleEventType.STARTED)
		{
			switchToBattle(e);
			
		}
		if(e.getType() == BattleEventType.ENDED)
		{
			battle.view.transformed.setVisible(false);
			world.getGUImap().setVisible(true);
			battle.view.setVisible(false);
			battle = null;
			if(e.getWinner() != world.game.getPlayer().getActiveFighter())
			{
				JOptionPane.showMessageDialog(world.getGUImap(), "Foe Wins!","Winner",
					    JOptionPane.INFORMATION_MESSAGE);
				if(e.getWinner() instanceof NonPlayableFighter &&((NonPlayableFighter)e.getWinner()).isStrong()){
					world.fighter.setBounds(world.xAxis = 43*world.getGUImap().getWidth()/48,world.yAxis = 35*world.getGUImap().getHeight()/108,world.width = 17*world.getGUImap().getWidth()/192,world.height = 17*world.getGUImap().getHeight()/108);

				}
				else{
					world.fighter.setBounds(world.xAxis = 43*world.getGUImap().getWidth()/48,world.yAxis = 35*world.getGUImap().getHeight()/108,world.width = 17*world.getGUImap().getWidth()/192,world.height = 17*world.getGUImap().getHeight()/108);

				}
			}
			else
				JOptionPane.showMessageDialog(world.getGUImap(), "Fighter Wins!","Winner",
					    JOptionPane.INFORMATION_MESSAGE);
			Battle b = (Battle) e.getSource();
			if(((NonPlayableFighter) b.getFoe()).isStrong())
				world.fighter.setBounds(world.xAxis = 43*world.getGUImap().getWidth()/48,world.yAxis = 35*world.getGUImap().getHeight()/108,world.width = 17*world.getGUImap().getWidth()/192,world.height = 17*world.getGUImap().getHeight()/108);
			world.getGUImap().label.setText("<html>Name: "+world.getGame().getPlayer().getName()+"<br/>Explored Maps: "+world.getGame().getPlayer().getExploredMaps()+"</html>");
			world.getGUImap().label2.setText("<html>Name:"+world.getGame().getPlayer().getActiveFighter().getName()+"<br/>Type: "+world.type+"<br/>Level: "+world.getGame().getPlayer().getActiveFighter().getLevel()+"<br/>Target XP: "+world.getGame().getPlayer().getActiveFighter().getTargetXp()+"<br/> XP: "+  world.getGame().getPlayer().getActiveFighter().getXp()+"<br/>Max HealthPoints: "+world.getGame().getPlayer().getActiveFighter().getMaxHealthPoints()+"<br/>Physical Damage "+world.getGame().getPlayer().getActiveFighter().getPhysicalDamage()+"<br/>Blast Damage: "+world.getGame().getPlayer().getActiveFighter().getBlastDamage()+"<br/>Max KI "+world.getGame().getPlayer().getActiveFighter().getMaxKi()+"<br/>Max Stamina "+world.getGame().getPlayer().getActiveFighter().getMaxStamina()+"<br/>Ability Points: "+world.getGame().getPlayer().getActiveFighter().getAbilityPoints()+"</html>");
			world.getGUImap().label3.setText("<html>Senzu Beans: "+world.getGame().getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  world.getGame().getPlayer().getDragonBalls()+"</html>");
		}
		if(e.getType() == BattleEventType.BLOCK){
			if(e.getCurrentOpponent()==battle.battle.getMe())
				
				battle.view.meBlock.setVisible(true);
			else
				battle.view.foeBlock.setVisible(true);
			
			battle.view.foeInfo.setValue(100*battle.battle.getFoe().getHealthPoints()/battle.battle.getFoe().getMaxHealthPoints());
			battle.view.meInfo.setValue(100-(100*battle.battle.getMe().getHealthPoints()/battle.battle.getMe().getMaxHealthPoints()));
			
			battle.view.meStamina.setValue(7-battle.battle.getMe().getStamina());
			battle.view.meKi.setValue(7-battle.battle.getMe().getKi());
			
			battle.view.foeStamina.setValue(battle.battle.getFoe().getStamina());
			battle.view.foeKi.setValue(battle.battle.getFoe().getKi());

		}
		if(e.getType() == BattleEventType.USE){
			battle.view.meSenzo.setText(world.game.getPlayer().getSenzuBeans()+"  ");
		}
		if(e.getType() == BattleEventType.ATTACK){
			battle.view.foeInfo.setValue(100*battle.battle.getFoe().getHealthPoints()/battle.battle.getFoe().getMaxHealthPoints());
			battle.view.meInfo.setValue(100-(100*battle.battle.getMe().getHealthPoints()/battle.battle.getMe().getMaxHealthPoints()));
			
			battle.view.meStamina.setValue(8-battle.battle.getMe().getStamina());
			battle.view.meKi.setValue(8-battle.battle.getMe().getKi());
			battle.view.foeStamina.setValue(battle.battle.getFoe().getStamina());
			battle.view.foeKi.setValue(battle.battle.getFoe().getKi());
			if(battle.battle.getMe() instanceof Saiyan &&((Saiyan)battle.battle.getMe()).isTransformed())
				battle.view.me.rechangeImage("ProjectFiles\\Pictures\\Transformed.gif");
			if(battle.battle.getMe() instanceof Saiyan &&!((Saiyan)battle.battle.getMe()).isTransformed())
				battle.view.me.rechangeImage("ProjectFiles\\Pictures\\"+world.icon);
		}
		if(e.getType() == BattleEventType.NEW_TURN){
			if(battle.battle.isMeBlocking())
				battle.view.meBlock.setVisible(true);
			else
				battle.view.meBlock.setVisible(false);
			if(battle.battle.isFoeBlocking())
				battle.view.foeBlock.setVisible(true);
			else
				battle.view.foeBlock.setVisible(false);
			
			if(e.getCurrentOpponent() == battle.battle.getFoe())
			{
				battle.view.turnMe.setVisible(false);
				battle.view.turnFoe.setVisible(true);
			}
			else
			{
				battle.view.turnMe.setVisible(true);
				battle.view.turnFoe.setVisible(false);
			}
			if(e.getCurrentOpponent() == battle.battle.getFoe())
			{
//				while(true)
//				{
//					try {
//						
//						battle.battle.play();
//						break;
//					} catch (NotEnoughKiException e1) {
//						continue;
//					}
//				}
				
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	while(true)
								{
									try {
										
										battle.battle.play();
										break;
									} catch (NotEnoughKiException e1) {
										continue;
									}
								}
				            }
				        }, 
				        2000 
				);
				battle.choose.updateUI();
				battle.view.setFocusable(true);
			}
				
			else
			{
				battle.rechoose();
			}
		}
		
	}
	
	

	@Override
	public void use(){
		try {
			battle.battle.use(world.game.getPlayer(), Collectible.SENZU_BEAN);
		} catch (NotEnoughSenzuBeansException e1) {
			JOptionPane.showMessageDialog(battle.view, e1.getMessage(),"Not Enough Senzu Beans",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void switchToChooseFighter(String t) {
		chooseFighter = new DragonBallChooseFighterGUI(t);
		chooseFighter.setListener(this);
		if(mainMenu != null)
		{
			mainMenu.getGameView().setVisible(false);
			mainMenu = null;
		}
	}



	@Override
	public void switchToWorld(String t ,String x, char race) {
		if(t.equals(""))
		{
			if(x.equals("New_Fighter"))
				x+=world.game.getPlayer().getFighters().size();
			world.game.getPlayer().createFighter(race, x);
			world.getGUImap().setVisible(true);
			chooseFighter.getView().setVisible(false);
			chooseFighter = null;
			
		}
		else if(t.equals("load"))
		{
			Game game;
			try {
				game = new Game();
				try {
				    game.load("ProjectFiles\\Save\\save.ser");
				    world = new DragonBallWorldGUI_3D(game);
				    world.getGame().setListener(this);
				    mainMenu.getGameView().setVisible(false);
				    world.listener = this;
				    mainMenu = null;
				   } catch (ClassNotFoundException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				   } catch (FileNotFoundException e) {
				    JOptionPane.showMessageDialog(mainMenu.getGameView(), "There is no Pre-Saved game","no pre-saved game",
				         JOptionPane.ERROR_MESSAGE);
				   } catch (IOException e) {
					   JOptionPane.showMessageDialog(mainMenu.getGameView(), "There is no Pre-Saved game","no pre-saved game",
						         JOptionPane.ERROR_MESSAGE);
				   }
				
			} catch (IOException e) {
				
			}
    
			
		}
		
		else
		{
			world = new DragonBallWorldGUI_3D(t,x,race);
			world.getGame().setListener(this);
			chooseFighter.getView().setVisible(false);
			world.listener = this;
			chooseFighter = null;
		}
		
		
	}



	@Override
	public void switchToPauseMenu() {
		pauseMenu = new DragonBallPauseMenuGUI();
		pauseMenu.setListener(this);
		if(dragon!=null)
			dragon.view.setVisible(false);
		else if(battle!=null)
			battle.view.setVisible(false);
		else if(world!=null)
			world.getGUImap().setVisible(false);
		else if(chooseFighter!=null)
			chooseFighter.getView().setVisible(false);
		
	}



	@Override
	public void switchToDragon(Dragon d) {
		
		this.dragon = new DragonBallDragonGUI(d);
		this.dragon.setListener(this);
		world.getGUImap().setVisible(false);
		
		
	}



	@Override
	public void switchToBattle(BattleEvent e) {
		battle = new DragonBallBattleGUI((Battle)e.getSource());
		battle.setListener(this);
		battle.view.me.rechangeImage("ProjectFiles\\Pictures\\"+world.icon);
		if(((NonPlayableFighter)((Battle)e.getSource()).getFoe()).isStrong())
			battle.view.foe.rechangeImage("ProjectFiles\\Pictures\\vegeta.gif");
		battle.view.meStamina.setValue(8-battle.battle.getMe().getStamina());
		battle.view.meStaminaM.setText(battle.view.meStaminaM.getText()+battle.battle.getMe().getMaxStamina()+"");
		battle.view.meKi.setValue(8-battle.battle.getMe().getKi());
		battle.view.meKiM.setText(battle.view.meKiM.getText()+battle.battle.getMe().getMaxKi()+"");
		battle.view.foeStamina.setValue(battle.battle.getFoe().getStamina());
		battle.view.foeStaminaM.setText(battle.view.foeStaminaM.getText()+battle.battle.getFoe().getMaxStamina()+"");
		battle.view.foeKi.setValue(battle.battle.getFoe().getKi());
		battle.view.foeKiM.setText(battle.view.foeKiM.getText()+battle.battle.getFoe().getMaxKi()+"");
		battle.view.meSenzo.setText(world.game.getPlayer().getSenzuBeans()+battle.view.meSenzo.getText());
		world.getGUImap().setVisible(false);
		
	}
	
	@Override
	public void switchToDragonWish(DragonWish wish) {
		//at action of dragon
		world.getGame().getPlayer().chooseWish(this.dragon.wish);
		world.getGUImap().label2.setText("<html>Name:"+world.getGame().getPlayer().getActiveFighter().getName()+"<br/>Type: "+world.type+"<br/>Level: "+world.getGame().getPlayer().getActiveFighter().getLevel()+"<br/>Target XP: "+world.getGame().getPlayer().getActiveFighter().getTargetXp()+"<br/> XP: "+  world.getGame().getPlayer().getActiveFighter().getXp()+"<br/>Max HealthPoints: "+world.getGame().getPlayer().getActiveFighter().getMaxHealthPoints()+"<br/>Physical Damage "+world.getGame().getPlayer().getActiveFighter().getPhysicalDamage()+"<br/>Blast Damage: "+world.getGame().getPlayer().getActiveFighter().getBlastDamage()+"<br/>Max KI "+world.getGame().getPlayer().getActiveFighter().getMaxKi()+"<br/>Max Stamina "+world.getGame().getPlayer().getActiveFighter().getMaxStamina()+"<br/>Ability Points: "+world.getGame().getPlayer().getActiveFighter().getAbilityPoints()+"</html>");
		world.getGUImap().label3.setText("<html>Senzu Beans: "+world.getGame().getPlayer().getSenzuBeans()+"<br/> Dragon Balls: "+  world.getGame().getPlayer().getDragonBalls()+"</html>");
		world.getGUImap().setVisible(true);
		world.getGUImap().revalidate();
		world.fighter.revalidate();
		this.dragon.view.setVisible(false);
		this.dragon = null;
		
		
	}



	@Override
	public void save() {

		Game g;
		try {
			g = new Game();
			g.loadGame(world.game);
			g.save("ProjectFiles\\Save\\save.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new  FileOutputStream("ProjectFiles\\Save\\pos.ser"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	



	@Override
	public void resume() {
		if(dragon!=null)
			dragon.view.setVisible(true);
		else if(battle!=null)
			battle.view.setVisible(true);
		else if(chooseFighter!=null)
			chooseFighter.getView().setVisible(true);
		else  if(world!=null)
			world.getGUImap().setVisible(true);
		pauseMenu.view.setVisible(false);
		pauseMenu = null;
		
	}



	@Override
	public void swichToMainMenu() {
		battle = null;
		dragon = null;
		chooseFighter = null;
		mainMenu = null;
		pauseMenu = null;
		world = null;
		
		String[] args = null;
		try {
			main(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


	public static void main(String[] args) throws IOException {
		@SuppressWarnings("unused")
		DragonBallAdventuresGUI OurGame = new DragonBallAdventuresGUI();
	}
	
}
