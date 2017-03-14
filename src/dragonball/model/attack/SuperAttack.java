package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.AlreadyTransformedException;
import dragonball.model.exceptions.NotEnoughKiException;

@SuppressWarnings("serial")
public class SuperAttack extends Attack {
	public SuperAttack(String name, int damage){
		super(name, damage);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		if(getName().equals("Maximum Charge"))
			return 0;
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed()){
				return (int) ((double)(getDamage() +attacker.getBlastDamage())*1.25);
		}
		return (getDamage() + attacker.getBlastDamage());
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException{
		/*if(getName().equals("Maximum Charge")){
			
			attacker.setKi(attacker.getKi()+3);
			return;
		}*/
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed()){
			if(defenderBlocking)
				super.onUse(attacker, defender, defenderBlocking);
			else
				defender.setHealthPoints(defender.getHealthPoints()-getAppliedDamage(attacker));
		}
		else{
			if(attacker.getKi() ==0)
				throw new NotEnoughKiException(1, 0);
			if(defenderBlocking)
				{
				super.onUse(attacker, defender, defenderBlocking);
				attacker.setKi(attacker.getKi()-1);
				}
			else{
				defender.setHealthPoints(defender.getHealthPoints()-getAppliedDamage(attacker));
				attacker.setKi(attacker.getKi()-1);
			}
		}
	}
}

/*if(defenderBlocking && attacker.getKi() >= 1)
{
	super.onUse(attacker, defender, defenderBlocking);
	attacker.setKi(attacker.getKi()-1);
}
else{
	if((attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed())){
		defender.setHealthPoints(defender.getHealthPoints() - this.getAppliedDamage(attacker));
	}
	else
		if(attacker.getKi() > 0)
		{
			defender.setHealthPoints(defender.getHealthPoints() - this.getAppliedDamage(attacker));
			attacker.setKi(attacker.getKi()-1);
		}
		else
			return;//handle the GUI massage

}*/

