package com.cool.item;

import com.cool.player.Player;
import com.cool.tile.TileObstacle;

public class ItemTileObstacle extends Item {

	public TileObstacle t;
	
	public ItemTileObstacle(TileObstacle tile) {
		super(tile.getTexture(), tile.getTileName(), tile.getDisplayName());
		this.t = tile;
		this.maxStackSize = 99;
	}
	@Override
	public boolean onUse(Player p) {
		if(p.getFacingObstacle() == null) {
			p.setFacingObstacle(t);
			p.takeItem(this, 1);
			return true;
		}
		return false;
	}

}
