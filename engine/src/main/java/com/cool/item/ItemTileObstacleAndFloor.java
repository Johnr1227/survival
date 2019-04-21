package com.cool.item;

import com.cool.player.Player;
import com.cool.tile.TileObstacleAndFloor;
import com.cool.tile.Tiles;

public class ItemTileObstacleAndFloor extends ItemTileObstacle {

	public ItemTileObstacleAndFloor(TileObstacleAndFloor tile) {
		super(tile);
	}

	@Override
	public boolean onUse(Player p) {
		return super.onUse(p);
	}

	@Override
	public boolean onSecondaryUse(Player p) {
		if (p.getFacingTile() == null || p.getFacingTile() == Tiles.GRASS) {
			p.setFacingTile(t);
			p.takeItem(this, 1);
			return true;
		}
		return false;
	}
}
