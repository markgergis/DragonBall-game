package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;

@SuppressWarnings("serial")
public class FoeCell extends Cell {

	private NonPlayableFighter foe;

	public FoeCell(NonPlayableFighter foe) {
		this.foe = foe;
	}

	public String toString() {
		if (foe.isStrong()) {
			return "[b]";
		} else {
			return "[w]";
		}
	}

	public NonPlayableFighter getFoe() {
		return foe;
	}

	@Override
	public void onStep() {
		if (getListener() != null) {
			this.getListener().onFoeEncountered(foe);

		}
	}

}
