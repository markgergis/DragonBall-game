package dragonball.model.battle;

import java.util.EventListener;

public interface BattleListener extends EventListener{
	void onBattleEvent(BattleEvent e);
	
}
