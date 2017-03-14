package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

@SuppressWarnings("serial")
public class Earthling extends PlayableFighter {
	public Earthling(String name) {
		super(name,1250,50,50,4,4,new ArrayList<SuperAttack>(),new ArrayList<UltimateAttack>());
	}
	
	public Earthling(String name, int level, int xp, int targetXp,
			int maxHealthPoints, int blastDamage, int physicalDamage,
			int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage,
				physicalDamage, abilityPoints, maxKi, maxStamina, superAttacks,
				ultimateAttacks);
	}

	@Override
	public void onAttackerTurn() {
		setStamina(getStamina()+1);
		setKi(getKi()+1);
		
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina()+1);
		
	}
	
	public char getType()
	{
		return 'E';
	}
//M2	

}
