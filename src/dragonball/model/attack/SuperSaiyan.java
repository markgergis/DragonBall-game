package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.AlreadyTransformedException;
import dragonball.model.exceptions.NotEnoughKiException;

@SuppressWarnings("serial")
public class SuperSaiyan extends UltimateAttack{

	public SuperSaiyan() {
		super("Super Saiyan", 0);
	}
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException{
			Saiyan attack = (Saiyan)attacker;
			if(attack.getKi() >=3)
				((Saiyan) attacker).setTransformed(true);
			else
				throw new NotEnoughKiException(3, attacker.getKi());
		
	}
}
