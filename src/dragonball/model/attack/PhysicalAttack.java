package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.AlreadyTransformedException;
import dragonball.model.exceptions.NotEnoughKiException;

@SuppressWarnings("serial")
public class PhysicalAttack extends Attack {

	public PhysicalAttack() {
		super("Physical Attack", 50);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		if(attacker instanceof Saiyan && ((Saiyan)attacker).isTransformed()){
				return (int) ((double)(attacker.getPhysicalDamage()+50)*1.25);
		}
		return (attacker.getPhysicalDamage()+50);
		
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		if(defenderBlocking)
			super.onUse(attacker, defender, defenderBlocking);
		else
		{
			defender.setHealthPoints(defender.getHealthPoints()-this.getAppliedDamage(attacker));
		}
		attacker.setKi(attacker.getKi()+1);
		
	}

}
