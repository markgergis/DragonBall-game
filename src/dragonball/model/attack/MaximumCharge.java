package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

@SuppressWarnings("serial")
public class MaximumCharge extends SuperAttack {

	public MaximumCharge() {
		super("Maximum Charge", 0);
	}
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) {
			attacker.setKi(attacker.getKi()+3);
		
	}
	
}
