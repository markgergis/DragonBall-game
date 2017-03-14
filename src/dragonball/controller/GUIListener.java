package dragonball.controller;

import java.io.IOException;
import java.util.EventListener;

import dragonball.model.battle.BattleEvent;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;

public interface GUIListener extends EventListener{

	public void switchToChooseFighter(String t);
	
	public void resume();
	
	public void swichToMainMenu();
	
	public void switchToPauseMenu();
	
	public void use();
	
	public void save();
	
	public void switchToBattle(BattleEvent e);


	public void switchToDragon(Dragon d);

	public void switchToWorld(String t,String x, char race);
	
	
	public void switchToDragonWish(DragonWish wish);
}
