package dragonball.model.character;

import java.io.Serializable;

//A class representing a single character belonging to the player. No objects of type
//Character can be instantiated.

@SuppressWarnings("serial")
public abstract class Character implements Serializable{
	private String name;

	public Character(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
