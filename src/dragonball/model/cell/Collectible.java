package dragonball.model.cell;

public enum Collectible {

	SENZU_BEAN, DRAGON_BALL;

	public String toString() {
		if (this == SENZU_BEAN)
			return "senzu bean";
		else
			return "dragon ball";
	}

}
