package com.cool.tiles;

import com.cool.SoundType;
import com.cool.Textures;
import com.cool.item.Items;
import com.cool.lib.Vertex;
import com.cool.player.Player;
import com.cool.tile.TileObstacle;

public class TileWorkbench extends TileObstacle {
	
	public TileWorkbench() {
		setTexture(Textures.WORKBENCH);
		setTileName("workbench");
		this.soundType = SoundType.TREE;
	}
	@Override
	public String getDisplayName() {
		return "Workbench";
	}
	@Override
	public void onBroken(Player p, Vertex pos) {
		p.giveItem(Items.WORKBENCH,1);
		super.onBroken(p,pos);
	}
}
