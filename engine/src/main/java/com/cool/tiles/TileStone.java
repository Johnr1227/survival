package com.cool.tiles;

import com.cool.Textures;
import com.cool.item.Items;
import com.cool.lib.Vertex;
import com.cool.player.Player;
import com.cool.tile.SoundType;
import com.cool.tile.TileObstacle;

public class TileStone extends TileObstacle {
	
	public TileStone() {
		setTexture(Textures.STONE);
		setTileName("stone");
		this.soundType = SoundType.STONE;
	}
	@Override
	public String getDisplayName() {
		return "Stone";
	}
	@Override
	public void onBroken(Player p, Vertex pos) {
		p.giveItem(Items.stone,1);
		super.onBroken(p,pos);
	}
}
