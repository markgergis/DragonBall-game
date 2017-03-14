package dragonball.model.game;

import java.io.Serializable;
import java.util.EventListener;

import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.dragon.Dragon;

public interface GameListener extends EventListener, Serializable {

	public void onDragonCalled(Dragon dragon);

	public void onCollectibleFound(Collectible collectible);

	public void onBattleEvent(BattleEvent e);

}
