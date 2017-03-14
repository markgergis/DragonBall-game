package dragonball.model.character.fighter;

import java.io.Serializable;
import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.NonPlayableCharacter;

@SuppressWarnings("serial")
public class NonPlayableFighter extends Fighter implements NonPlayableCharacter , Serializable {
	private boolean strong;

	public NonPlayableFighter(String name, int level, int maxHealthPoints,
			int blastDamage, int physicalDamage, int maxKi, int maxStamina,
			boolean strong, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, maxHealthPoints, blastDamage, physicalDamage, maxKi,
				maxStamina, superAttacks, ultimateAttacks);

		this.strong = strong;
	}

	public boolean isStrong() {
		return strong;
	}
//M2
	@Override
	public void onAttackerTurn() {
		setStamina(getStamina()+1);
		
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina()+1);
	}
}
