package com.cool.tiles;

import com.cool.Textures;
import com.cool.item.Items;
import com.cool.lib.Color;
import com.cool.lib.Renderer;
import com.cool.lib.Vertex;
import com.cool.player.Player;
import com.cool.tile.SoundType;
import com.cool.tile.TileObstacleAndFloor;

public class TileWoodPlanks extends TileObstacleAndFloor {
	
	public TileWoodPlanks() {
		setTexture(Textures.WOOD_PLANKS);
		setTileName("planks");
		this.soundType = SoundType.WOOD;
	}
	@Override
	public String getDisplayName() {
		return "Wood Planks";
	}
	@Override
	public void onBroken(Player p, Vertex pos) {
		p.giveItem(Items.WOOD_PLANKS,1);
		super.onBroken(p,pos);
	}
	@Override
	public void render(Player p, Vertex pos, int ogX1, int ogY1, int ogX2, int ogY2, int tWidth) {
		if(p.world.obstacles[pos.x][pos.z] == this) {
			super.render(p, pos, ogX1, ogY1, ogX2, ogY2, tWidth);
		} else {
			Color c;
			if(pos.z > 0) {
				if(p.world.obstacles[pos.x][pos.z-1] == null) {
					c = new Color(0.9f,0.9f,0.9f);
				} else {
					c = new Color(0.8f, 0.8f, 0.8f);
				}
			} else {
				c = new Color(1f,1f,1f);
			}
			Renderer.drawColoredTexture(ogX1, ogY1, ogX2, ogY2, texture, c);
		}
	}
}
