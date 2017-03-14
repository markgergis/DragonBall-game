package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

@SuppressWarnings("serial")
public class Saiyan extends PlayableFighter {

	private boolean transformed;

	public Saiyan(String name) {
		super(name, 1000, 150, 100, 5, 3, new ArrayList<SuperAttack>(),
				new ArrayList<UltimateAttack>());
	}

	public Saiyan(String name, int level, int xp, int targetXp,
			int maxHealthPoints, int blastDamage, int physicalDamage,
			int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage,
				physicalDamage, abilityPoints, maxKi, maxStamina, superAttacks,
				ultimateAttacks);
	}

	public boolean isTransformed() {
		return transformed;
	}

	public void setTransformed(boolean transformed) {
		this.transformed = transformed;
	}

	@Override
	public void onAttackerTurn() {
		setStamina(getStamina() + 1);
		if (transformed) {
			setKi(getKi() - 1);
			if (getKi() == 0) {
				transformed = false;
				setStamina(0);
			}
		}

	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1);
		if (transformed) {

			setKi(getKi() - 1);
			if (getKi() == 0) {
				transformed = false;
				setStamina(0);
			}
		}

	}
	public char getType()
	{
		return 'S';
	}

}
