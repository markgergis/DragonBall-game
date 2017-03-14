package dragonball.model.attack;

import java.io.Serializable;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.exceptions.AlreadyTransformedException;
import dragonball.model.exceptions.NotEnoughKiException;

@SuppressWarnings("serial")
public abstract class Attack implements Serializable {

	private String name;
	private int damage;

	public Attack(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	
	//M2
	
	public abstract int getAppliedDamage(BattleOpponent attacker);
	
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException{
		int damage = getAppliedDamage(attacker);
		while(damage >0 && defender.getStamina() >0){
			damage-=100;
			defender.setStamina(defender.getStamina()-1);
			
		}
		if(defender.getStamina()== 0 && damage>0)
			defender.setHealthPoints(defender.getHealthPoints() - damage);
		
	}
	//M2
}
