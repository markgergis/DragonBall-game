package dragonball.model.character;

import dragonball.model.character.fighter.Fighter;

public class Trainer extends NonFighter{

	private int addedDamage;
	
	
	public int getAddedDamage() {
		return addedDamage;
	}



	public Trainer(String name , int addedDamage) {
		super(name);
		this.addedDamage = addedDamage;
	}
	
	
	public void helpFighter(Fighter c){
		super.helpFighter(c);
		c.setBlastDamage(c.getBlastDamage()+addedDamage);
		c.setPhysicalDamage(c.getPhysicalDamage()+addedDamage);
	}

}
