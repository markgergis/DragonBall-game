package dragonball.model.character.fighter;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.Character;

import java.util.ArrayList;
//Subclass of the (Character) class representing the ghters in the game. No objects of
//type Fighter can be instantiated. The class implements the BattleOpponent interface.

@SuppressWarnings("serial")
public abstract class Fighter extends Character implements BattleOpponent  {
	private int level;
	private int blastDamage;
	private int physicalDamage;
	private int healthPoints;
	private int maxHealthPoints;
	private int ki;
	private int maxKi;
	private int stamina;
	private int maxStamina;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;

	public Fighter(String name, int level, int maxHealthPoints,
			int blastDamage, int physicalDamage, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name);
		this.level = level;
		this.maxHealthPoints = maxHealthPoints;
		this.blastDamage = blastDamage;
		this.physicalDamage = physicalDamage;
		this.maxKi = maxKi;
		this.maxStamina = maxStamina;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBlastDamage() {
		return blastDamage;
	}

	public void setBlastDamage(int blastDamage) {
		this.blastDamage = blastDamage;
	}

	public int getPhysicalDamage() {
		return physicalDamage;
	}

	public void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	// M2
	public void setHealthPoints(int healthPoints) {
		if (healthPoints <= 0)
			this.healthPoints = 0;
		else if (healthPoints > maxHealthPoints)
			this.healthPoints = maxHealthPoints;
		else
			this.healthPoints = healthPoints;
	}// M2

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}

	public int getKi() {
		return ki;
	}

	// M2
	public void setKi(int ki) {
		if (ki < 0)
			this.ki = 0;
		else if (ki > maxKi)
			this.ki = maxKi;
		else
			this.ki = ki;
	}

	public void setStamina(int stamina) {
		if (stamina < 0)
			this.stamina = 0;
		else if (stamina > maxStamina)
			this.stamina = maxStamina;
		else
			this.stamina = stamina;
	}
	public abstract void onAttackerTurn();
	
	public abstract void onDefenderTurn();

	// M2

	public int getMaxKi() {
		return maxKi;
	}

	public void setMaxKi(int maxKi) {
		this.maxKi = maxKi;
	}

	public int getStamina() {
		return stamina;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

}
