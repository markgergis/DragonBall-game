package dragonball.model.character;

import dragonball.model.character.fighter.Fighter;

public class Healer extends NonFighter{

	
	private int possibleHPrecovery;
	
	public int getPossibleHPrecovery() {
		return possibleHPrecovery;
	}

	public Healer(String name,int possibleHPrecovery) {
		super(name);
		this.possibleHPrecovery = possibleHPrecovery;
	}

	
	public void helpFighter(Fighter c){
		super.helpFighter(c);
		c.setMaxHealthPoints(c.getMaxHealthPoints() + possibleHPrecovery);
		c.setHealthPoints(c.getHealthPoints() + possibleHPrecovery);
	}
}
