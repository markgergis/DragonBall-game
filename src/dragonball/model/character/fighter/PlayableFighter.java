package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.PlayableCharacter;

@SuppressWarnings("serial")
public abstract class PlayableFighter extends Fighter implements
		PlayableCharacter {
	private int xp;
	private int targetXp;
	private int abilityPoints;


	public PlayableFighter(String name, int level, int xp, int targetXp,
			int maxHealthPoints, int blastDamage, int physicalDamage,
			int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, maxHealthPoints, blastDamage, physicalDamage, maxKi,
				maxStamina, superAttacks, ultimateAttacks);
		this.xp = xp;
		this.targetXp = targetXp;
		this.abilityPoints = abilityPoints;
		this.setStamina(maxStamina);
		this.setKi(0);
		this.setHealthPoints(maxHealthPoints);
	}

	public PlayableFighter(String name, int maxHealthPoints, int blastDamage,
			int physicalDamage, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		this(name, 1, 0, 10, maxHealthPoints, blastDamage, physicalDamage, 0,
				maxKi, maxStamina, superAttacks, ultimateAttacks);
		this.setStamina(maxStamina);
		this.setKi(0);
		this.setHealthPoints(maxHealthPoints);
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		while (xp >= this.targetXp) {
			xp -= this.targetXp;
			this.setLevel(this.getLevel() + 1);
			this.targetXp += 20;
			this.abilityPoints += 2;
			this.xp = 0;
		}
		this.xp = xp;
	}

	public int getTargetXp() {
		return targetXp;
	}

	public void setTargetXp(int targetXp) {
		this.targetXp = targetXp;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(int abilityPoints) {
		this.abilityPoints = abilityPoints;
	}
	
	public abstract char getType();

}
