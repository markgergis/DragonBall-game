package dragonball.model.battle;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;



//Interface containing the methods available to ghters within battle while acting against
//an opponent.
public interface BattleOpponent {

	public int getLevel();

	public void setLevel(int level);

	public int getBlastDamage();

	public void setBlastDamage(int blastDamage);

	public int getPhysicalDamage();

	public void setPhysicalDamage(int physicalDamage);

	public int getHealthPoints();

	public void setHealthPoints(int healthPoints);

	public int getMaxHealthPoints();

	public void setMaxHealthPoints(int maxHealthPoints);

	public int getKi();

	public void setKi(int ki);

	public int getMaxKi();

	public void setMaxKi(int maxKi);

	public int getStamina();

	public void setStamina(int stamina);

	public int getMaxStamina();

	public void setMaxStamina(int maxStamina);

	public ArrayList<SuperAttack> getSuperAttacks();

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks);

	public ArrayList<UltimateAttack> getUltimateAttacks();

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks);
	//M2
	public void onAttackerTurn();
	
	public void onDefenderTurn();
	
	
	//M2
}
