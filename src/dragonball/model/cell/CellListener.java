package dragonball.model.cell;

import java.util.EventListener;

import dragonball.model.character.fighter.NonPlayableFighter;

public interface CellListener extends EventListener {
	void onFoeEncountered(NonPlayableFighter foe);

	void onCollectibleFound(Collectible collectible);
}
