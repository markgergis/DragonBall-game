package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.AlreadyTransformedException;
import dragonball.model.exceptions.NotEnoughKiException;

@SuppressWarnings("serial")
public class UltimateAttack extends Attack{

	public UltimateAttack(String name, int damage) {
		super(name, damage);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		if(getName().equals("Super Saiyan"))
		{
				return 0;
		}
		else
			if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed()){
					return (int) ((double)(getDamage() +attacker.getBlastDamage())*1.25);
			}
		return (getDamage() + attacker.getBlastDamage());
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException{
		/*if(getName().equals("Super Saiyan")){
			Saiyan attack = (Saiyan)attacker;
			if(attack.getKi() >=3)
				((Saiyan) attacker).setTransformed(true);
			return;
		}*/
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed()){
			if(defenderBlocking)
				super.onUse(attacker, defender, defenderBlocking);
			else
				defender.setHealthPoints(defender.getHealthPoints()-getAppliedDamage(attacker));
		}
		else{
			if(attacker.getKi() <3)
				throw new NotEnoughKiException(3, attacker.getKi());
			if(defenderBlocking)
				{
				super.onUse(attacker, defender, defenderBlocking);
				attacker.setKi(attacker.getKi()-3);
				}
			else{
				defender.setHealthPoints(defender.getHealthPoints()-getAppliedDamage(attacker));
				attacker.setKi(attacker.getKi()-3);
			}
		}
		
	}
	
}

/*if(defenderBlocking && attacker.getKi() >= 3)
{
	super.onUse(attacker, defender, defenderBlocking);
	attacker.setKi(attacker.getKi()-3);
}
else{
	if(attacker.getKi() >= 3){
		defender.setHealthPoints(defender.getHealthPoints() - this.getAppliedDamage(attacker));
		attacker.setKi(attacker.getKi()-3);
	}
	else
		return;//handle the GUI massage
	
}*/


