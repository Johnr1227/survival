package com.cool.tile;

import com.cool.lib.Renderer;
import com.cool.lib.Texture;
import com.cool.lib.Vertex;
import com.cool.player.Player;

public class TileObstacle extends Tile {
	
	public TileObstacle(Texture texture, String name, String displayName, int layer) {
		this.texture = texture;
		this.tileName = name;
		this.displayName = displayName;
		this.layer = layer;
	}
	public TileObstacle() {
		
	}
	@Override
	public void render(Player p, Vertex pos, int ogX1, int ogY1, int ogX2, int ogY2, int tWidth) {
		Renderer.drawTexture(ogX1, ogY1, ogX2, ogY2, texture);
	}
}
