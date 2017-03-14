package dragonball.model.character;

import dragonball.model.character.fighter.Fighter;

public abstract class NonFighter extends Character implements Spectator{

	
	public NonFighter(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void helpFighter(Fighter c) {
		c.setKi(c.getKi() + 1);
		c.setStamina(c.getStamina() + 1);
		
	}

	@Override
	public void cheerFighter(Fighter c) {
		if(c.getKi()>c.getStamina())
			c.setStamina(c.getStamina() + 2);
		else	
			c.setKi(c.getKi() + 3);
		
	}

}
