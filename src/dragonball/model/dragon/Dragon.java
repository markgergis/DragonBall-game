package dragonball.model.dragon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

@SuppressWarnings("serial")
public class Dragon implements Serializable {

	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;

	public Dragon(String name, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans,
			int abilityPoints) {
		this.name = name;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.abilityPoints = abilityPoints;
	}

	public String getName() {
		return name;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public DragonWish[] getWishes() {
		int size = 0;
		if (senzuBeans != 0)
			size++;
		if (abilityPoints != 0)
			size++;
		if (superAttacks.size() != 0)
			size++;
		if (ultimateAttacks.size() != 0)
			size++;
		DragonWish[] dragonWish = new DragonWish[size];
		int i = 0;
		Random r = new Random();
		if (senzuBeans != 0)
			dragonWish[i++] = new DragonWish(this, DragonWishType.SENZU_BEANS,
					senzuBeans);
		if (abilityPoints != 0)
			dragonWish[i++] = new DragonWish(this,
					DragonWishType.ABILITY_POINTS, abilityPoints);
		if (superAttacks.size() != 0)
			dragonWish[i++] = new DragonWish(this, DragonWishType.SUPER_ATTACK,
					superAttacks.get(r.nextInt(superAttacks.size())));
		if (ultimateAttacks.size() != 0)
			dragonWish[i++] = new DragonWish(this,
					DragonWishType.ULTIMATE_ATTACK, ultimateAttacks.get(r
							.nextInt(ultimateAttacks.size())));

		return dragonWish;
	}

}
