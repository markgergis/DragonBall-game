package dragonball.model.player;

import java.io.Serializable;
import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;

//A class representing a single player playing the game
@SuppressWarnings("serial")
public class Player implements Serializable {
	private String name;
	private ArrayList<PlayableFighter> fighters;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int dragonBalls;
	private PlayableFighter activeFighter;
	private int exploredMaps;
	private PlayerListener listener;

	public Player(String name) {
		this(name, new ArrayList<PlayableFighter>(),
				new ArrayList<SuperAttack>(), new ArrayList<UltimateAttack>(),
				0, 0, null, 0);
	}

	public Player(String name, ArrayList<PlayableFighter> fighters,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans,
			int dragonBalls, PlayableFighter activeFighter, int exploredMaps) {

		this.name = name;
		this.fighters = fighters;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.dragonBalls = dragonBalls;
		this.activeFighter = activeFighter;
		this.exploredMaps = exploredMaps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters = fighters;
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

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public int getDragonBalls() {
		return dragonBalls;
	}

	public void setDragonBalls(int dragonBalls) {
		this.dragonBalls = dragonBalls;
	}

	public PlayableFighter getActiveFighter() {
		return activeFighter;
	}

	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}

	public int getExploredMaps() {
		return exploredMaps;
	}

	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}

	public int getMaxFighterLevel() {
		int max = fighters.get(0).getLevel();
		for (int i = 1; i < fighters.size(); i++) {
			max = Math.max(max, fighters.get(i).getLevel());
		}
		return max;
	}

	public void callDragon() {
		if (listener != null)
			listener.onDragonCalled();
	}

	public void chooseWish(DragonWish wish) {
		if (wish.getType() == DragonWishType.ABILITY_POINTS)
			activeFighter.setAbilityPoints(activeFighter.getAbilityPoints()
					+ wish.getAbilityPoints());
		if (wish.getType() == DragonWishType.SENZU_BEANS)
			senzuBeans += wish.getSenzuBeans();
		if (wish.getType() == DragonWishType.SUPER_ATTACK)
			superAttacks.add(wish.getSuperAttack());
		if (wish.getType() == DragonWishType.ULTIMATE_ATTACK)
			ultimateAttacks.add(wish.getUltimateAttack());

		
		
		if (listener != null)
			listener.onWishChosen(wish);
	}

	public PlayerListener getListener() {
		return listener;
	}

	public void setListener(PlayerListener listener) {
		this.listener = listener;
	}

	public void createFighter(char race, String name) {
		switch (race) {
		case 'E':
			fighters.add(new Earthling(name));
			break;
		case 'S':
			fighters.add(new Saiyan(name));
			break;
		case 'N':
			fighters.add(new Namekian(name));
			break;
		case 'F':
			fighters.add(new Frieza(name));
			break;
		case 'M':
			fighters.add(new Majin(name));
			break;
		}
		if (fighters.size() == 1)
			setActiveFighter(fighters.get(0));
	}

	public void upgradeFighter(PlayableFighter fighter, char fighterAttribute)
			throws NotEnoughAbilityPointsException {
		if (!fighters.contains(fighter))
			return;

		if (fighter.getAbilityPoints() == 0)
			throw new NotEnoughAbilityPointsException();

		fighter.setAbilityPoints(fighter.getAbilityPoints() - 1);

		switch (fighterAttribute) {
		case 'H':
			fighter.setMaxHealthPoints(fighter.getMaxHealthPoints() + 50);
			break;
		case 'B':
			fighter.setBlastDamage(fighter.getBlastDamage() + 50);
			break;
		case 'P':
			fighter.setPhysicalDamage(fighter.getPhysicalDamage() + 50);
			break;
		case 'K':
			fighter.setMaxKi(fighter.getMaxKi() + 1);
			break;
		case 'S':
			fighter.setMaxStamina(fighter.getMaxStamina() + 1);
			break;
		default:
			return;
		}
	}

	public void assignAttack(PlayableFighter fighter, SuperAttack newAttack,
			SuperAttack oldAttack) throws DuplicateAttackException,
			MaximumAttacksLearnedException {

		ArrayList<SuperAttack> x = fighter.getSuperAttacks();
		if (x.contains(newAttack))
			throw new DuplicateAttackException(newAttack);
		if (oldAttack == null) {
			if (fighter.getSuperAttacks().size() >= 4)
				throw new MaximumAttacksLearnedException();
			else {
				x.add(newAttack);
				fighter.setSuperAttacks(x);
			}
		} else {
			for (int i = 0; i < fighter.getSuperAttacks().size(); i++) {
				if (oldAttack == fighter.getSuperAttacks().get(i)) {
					x.set(i, newAttack);
					fighter.setSuperAttacks(x);
				}
			}
		}
	}

	public void assignAttack(PlayableFighter fighter, UltimateAttack newAttack,
			UltimateAttack oldAttack) throws NotASaiyanException,
			DuplicateAttackException, MaximumAttacksLearnedException {
		if (newAttack instanceof SuperSaiyan && !(fighter instanceof Saiyan))
			throw new NotASaiyanException();
		ArrayList<UltimateAttack> x = fighter.getUltimateAttacks();
		if (x.contains(newAttack))
			throw new DuplicateAttackException(newAttack);
		if (oldAttack == null) {
			if (fighter.getUltimateAttacks().size() >= 2)
				throw new MaximumAttacksLearnedException();
			else {
				x.add(newAttack);
				fighter.setUltimateAttacks(x);
			}
		} else {
			for (int i = 0; i < fighter.getUltimateAttacks().size(); i++) {
				if (oldAttack == fighter.getUltimateAttacks().get(i)) {
					x.set(i, newAttack);
					fighter.setUltimateAttacks(x);
				}
			}
		}
	}

}
