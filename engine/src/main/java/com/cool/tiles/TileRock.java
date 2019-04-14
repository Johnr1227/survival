package com.cool.tiles;

import com.cool.Textures;
import com.cool.item.Items;
import com.cool.lib.Vertex;
import com.cool.player.Player;
import com.cool.tile.SoundType;
import com.cool.tile.TileObstacle;

public class TileRock extends TileObstacle {
	
	public TileRock() {
		setTexture(Textures.ROCK);
		setTileName("rock");
		this.soundType = SoundType.ROCK;
	}
	@Override
	public String getDisplayName() {
		return "Rock";
	}
	@Override
	public void onBroken(Player p, Vertex pos) {
		p.giveItem(Items.carrot,3);
		super.onBroken(p,pos);
	}
}
