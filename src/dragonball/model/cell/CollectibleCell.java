package dragonball.model.cell;

@SuppressWarnings("serial")
public class CollectibleCell extends Cell {

	private Collectible collectible;

	public CollectibleCell(Collectible collectible) {
		super();
		this.collectible = collectible;
	}

	public String toString() {
		if (collectible == Collectible.SENZU_BEAN) {
			return ("[s]");
		} else {
			if (collectible == Collectible.DRAGON_BALL) {
				return ("[d]");
			}
		}
		return "";

	}

	public Collectible getCollectible() {
		return collectible;
	}

	// M2{

	@Override
	public void onStep() {
		if (getListener() != null)
			this.getListener().onCollectibleFound(collectible);

	}

	// M2}

}
