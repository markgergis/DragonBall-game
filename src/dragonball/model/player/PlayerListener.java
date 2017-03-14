package dragonball.model.player;


import java.util.EventListener;

import dragonball.model.dragon.DragonWish;

public interface PlayerListener extends EventListener{
	public void onDragonCalled();
	
	public  void onWishChosen(DragonWish wish);
}
