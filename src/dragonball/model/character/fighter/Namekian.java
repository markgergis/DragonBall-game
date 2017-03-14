package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

@SuppressWarnings("serial")
public class Namekian extends PlayableFighter {

	public Namekian(String name) {// change the blast damage form 0 to 50 for
									// the quiz
		super(name, 1350, 0, 50, 3, 5, new ArrayList<SuperAttack>(),
				new ArrayList<UltimateAttack>());
	}

	public Namekian(String name, int level, int xp, int targetXp,
			int maxHealthPoints, int blastDamage, int physicalDamage,
			int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage,
				physicalDamage, abilityPoints, maxKi, maxStamina, superAttacks,
				ultimateAttacks);
	}

	// M2
	@Override
	public void onAttackerTurn() {
		setStamina(getStamina() + 1);
		setHealthPoints(getHealthPoints() + 50);
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1);
		setHealthPoints(getHealthPoints() + 50);

	}
	public char getType()
	{
		return 'N';
	}
}
